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

## Copyright

Copyright Thomas Auinger (thomas@auinger.de), Germany. 

This source is intended to be used in conjunction with a training given
by me or one of my licensees. Therefore, you may use segments
of this code in your projects if you took part in such a training.

In any case it is not permitted to reuse all or parts of
this project for training purposes without permission.



