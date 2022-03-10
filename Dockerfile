# docker build -t pizza-backend .
# docker run -p 8080:8080 --name pizza-backend pizza-backend

FROM openjdk:11

ARG JAR_FILE=target/pizza-*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]