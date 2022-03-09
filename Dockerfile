# docker build -t integrata/spring-aufbau .
# docker run -p 8080:8080 --name spring-aufbau integrata/spring-aufbau:latest

FROM openjdk:11

ARG JAR_FILE=target/pizza-*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]