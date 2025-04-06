# создать образ с maven и docker
FROM jenkins/jenkins:lts
USER root
RUN apt-get update && apt-get install -y maven
RUN apt-get update && apt-get install -y docker.io