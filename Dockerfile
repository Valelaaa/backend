FROM openjdk:21
LABEL authors="Dille"

ARG JAR_FILE=*.jar

COPY . .
COPY target/*.jar app/app.jar

WORKDIR /app
ENTRYPOINT ["java","-jar","app.jar"]
