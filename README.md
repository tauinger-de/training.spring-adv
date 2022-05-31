# Pizza Pronto Backend

This is the source code of the backend application for the Spring Advanced training.

## Changelog

### v010

Initial branch -- here we define a very simple Spring Boot application.

### v020

Only minor changes to prepare for the upcoming pizza backend development.

A banner.txt file has been added as well as the DevTools dependency.

### v030 - Bean Definitions

Lots of classes for each of the three business domains added, especially
POJO model classes, services and exceptions.

This branch also adds first test classes to prepare for the next lesson.

### v040 - Testing

Adds unit and integration tests.

ProductService and OrderService methods are implemented and an in-memory
product repository developed and used.

### v050 - Data JPA

Adds spring-data-jpa and h2 dependencies to the project.

Introduces entity annotations, spring-data based repositories and
their incorporation into every service.

The CustomerRepositoryTest includes an example of accessing the H2 web-console during
test execution.

### v060 - Configuration

Makes use of configuration values in services and setup classes.

Introduces an unused OrderProperties holder class (which can be used instead of
`@Value` annotations) and a StringToDoubleMapConverter
implementation to parse the configuration string into a Map.

### v070 - Transactions

Makes use of the `@Transactional` annotation.

Introduces an order-count at Customer level.

### v080 - RESTful API

Replaces the `spring-boot-starter` dependency with `spring-boot-starter-web`.

Adds REST controllers.

Provides an export of a request collection, which can be imported into 
the REST client tool "Insomnia".

### v090 - E2E-Testing

Adds XyzRestControllerTest classes.

### v100 - Profiles, Logging & Monitoring

Adds `spring-boot-starter-actuator` and `micrometer-registry-prometheus`
dependencies. Also adds git build info generation to `pom.xml`.

Adds a range of `@Profile` annotations to most beans.

Makes use of Loggers instead of `System.out`.

Enables actuator endpoints.

### v110 - AOP

Adds the `aop` package including a LoggingAspect with two advices.

### v120 - RESTful API advanced

Adds exception handling and model conversion.

### v130 - SOAP API

Adds `spring-boot-starter-web-services` and `wsdl4j` dependencies as well
as source code generation to the pom.xml

**WARNING** - the project relies on generated source code from now on. This means
that a maven build (e.g. `mvn compile`) must be done before the project can
build inside the IDE.

### v150 - Integration

Adds `spring-integration-file` dependency.

Includes a file integration setup that scans a certain directory for JSON
based files containing pizza orders and performs a service call for those.

### v400 - Microservices

The monolithic backend is prepared for use in a microservice architecture.

* Dockerfile
  * Build image (before `mvn package`!): `docker build -t pizza-backend:1.400 .`
  * view image: `docker image ls`.
  * remove container: `docker rm -f pizza-backend-product`
  * start container: `docker run -p 8082:8080 --name pizza-backend-product -e "SPRING_PROFILES_ACTIVE=product" pizza-backend:1.400`
  * see logs from container: `docker logs --follow <containerIdOrName>`
  * start bash in container: `docker exec -it <containerIdOrName> bash` (or just `sh` instead of `bash`)
* new REST endpoints for the `customer` and `product` business domains.
* Swagger 3 support
  * http://localhost:8080/swagger-ui/index.html
  * http://localhost:8080/v3/api-docs/

## Copyright

Copyright Thomas Auinger (thomas@auinger.de), Germany. 

This source is intended to be used in conjunction with a training given
by me or one of my licensees. Therefore, you may use segments
of this code in your projects if you took part in such a training.

In any case it is not permitted to reuse all or parts of
this project for training purposes without permission.



