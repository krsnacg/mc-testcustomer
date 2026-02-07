FROM amazoncorretto:25-alpine3.20

RUN addgroup -S spring && adduser -S spring -G spring

USER spring:spring

ARG JAR_FILE=target/mc-testcustomer-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} /app/mc-testcustomer.jar

ENTRYPOINT [ "java", "-jar", "/app/mc-testcustomer.jar" ]