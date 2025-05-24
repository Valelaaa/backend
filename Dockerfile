FROM gradle:8.6-jdk AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean build

FROM openjdk:22-jdk
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/backend-0.0.1-SNAPSHOT.jar /app/backend.jar
COPY src/main/resources/enviroment/envApplication.properties /app/application.properties

ENTRYPOINT ["java", "-jar", "/app/backend.jar"]