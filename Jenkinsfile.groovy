#!groovy

properties([disableConcurrentBuilds()])
pipeline {
    agent any
    options {
        timestamps()
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build JAR') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    def dockerImage = docker.build("hello-world:latest", "-f Dockerfile .")
                }
            }
        }
        stage('Run Docker Container') {
            steps {
                script {
                    docker.image('hello-world:latest').run('-p 8888:8888')
                }
            }
        }
        stage('Publish to Nexus') {
            steps {
                script {
                    // Publish JAR to Nexus
                    nexusArtifactUploader(
                            nexusVersion: 'nexus3',
                            protocol: 'http',
                            nexusUrl: 'nexus:8081',
                            groupId: 'dh-osipov',
                            credentialsId: 'nexus-credentials',
                            version: '1.0.0',
                            repository: 'maven-releases',
                            artifacts: [
                                    [artifactId: 'dh-jenkins',
                                     classifier: '',
                                     extension : 'jar',
                                     file      : 'target/jenkins-1.0-SNAPSHOT.jar',
                                     type      : 'jar']
                            ]
                    )
                }
            }
        }
    }
    post {
        always {
            cleanWs()
        }
    }
}