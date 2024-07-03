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
    }
}