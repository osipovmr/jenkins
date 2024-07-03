FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/jenkins-1.0-SNAPSHOT.jar /app/jenkins.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "jenkins.jar"]


