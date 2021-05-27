FROM amazoncorretto:11-alpine

ADD backend-2.4.2.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8080