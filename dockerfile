FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ./acrh/target/acrh-2.0.0.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]