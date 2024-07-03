#FROM maven:3.8.4-openjdk-11 AS build
#WORKDIR /app
#COPY src /app/src
#COPY pom.xml /app
#RUN mvn clean package
#
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/jenkins-1.0-SNAPSHOT.jar /app/jenkins.jar
ENTRYPOINT ["java", "-jar", "jenkinsвщслук.jar"]


