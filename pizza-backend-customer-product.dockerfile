# docker build -t pizza-backend-customer-product .
# docker run -p 8082:8080 --name pizza-backend-customer-product pizza-backend-customer-product

FROM openjdk:11

ARG JAR_FILE=target/pizza-*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=customer,product", "-jar", "/app.jar"]