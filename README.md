# Pizza Pronto Backend

Die Beispielanwendung der Spring Aufbau Schulung.

## Changelog

### v400

In diesem Branch wird das monolithische Backend für die Nutzung in einer Microservice Architektur vorbereitet.

* Dockerfile
  * Image bauen (vorher `mvn package`!): `docker build -t pizza-backend:1.400 .`
  * Image sehen: `docker image ls`
  * Container entfernen: `docker rm -f pizza-backend-product`
  * Container starten: `docker run -p 8082:8080 --name pizza-backend-product -e "SPRING_PROFILES_ACTIVE=product" pizza-backend:1.400`
  * Logs von Container sehen: `docker logs --follow <containerIdOrName>`
  * Bash in Container starten: `docker exec -it <containerIdOrName>`
* neue REST Endpunkte für die `customer` und `product` business domains
* Swagger 3 Support
  * http://localhost:8080/swagger-ui/index.html
  * http://localhost:8080/v3/api-docs/
