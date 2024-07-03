FROM maven:3.8.4-openjdk-11 AS build
WORKDIR /app
COPY src /app/src
COPY pom.xml /app
RUN mvn clean package

FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]


FROM jenkins/jenkins:lts-jdk17
USER root
RUN apt-get update && apt-get install -y maven

USER jenkins