FROM gradle:8.6-jdk-alpine as build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

RUN gradle clean build
FROM openjdk:22-jdk
RUN mkdir /app
COPY . /app

EXPOSE 8080
COPY src/main/resources/enviroment/envApplication.properties src/main/resources/application.properties
ENTRYPOINT ["java", "-jar", "/app/build/libs/backend-0.0.1-SNAPSHOT.jar"]