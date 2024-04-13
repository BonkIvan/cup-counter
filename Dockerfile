FROM openjdk:21
COPY target/*.jar app.jar
LABEL authors="Admin"

ENTRYPOINT ["java", "-jar", "/app.jar"]