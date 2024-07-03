FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/jenkins-1.0-SNAPSHOT.jar /app/jenkins.jar
ENTRYPOINT ["java", "-jar", "jenkins.jar"]


