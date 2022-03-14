# Pizza Pronto Backend

Die Beispielanwendung der Spring Aufbau Schulung.

## Changelog

### v405

Das Backend wird umgebaut, so dass nur noch die `order` business domain enthalten ist und für
`customer` und `product` per Feign-Client auf separat deployte Microservices zugegriffen wird.

* meisten Klassen aus `customer` und `product` entfernt
* Einführung neuer API clients

### v400

In diesem Branch wird das monolithische Backend für die Nutzung in einer Microservice Architektur vorbereitet.

* Dockerfile
* neue REST Endpunkte für die `customer` und `product` business domains
* Swagger 3 Support
  * http://localhost:8080/swagger-ui/index.html
  * http://localhost:8080/v3/api-docs/
