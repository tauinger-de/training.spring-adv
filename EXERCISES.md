# Übung zu Kapitel "090 - API Integrations-Tests"

## a) CustomerRestControllerTest - getCustomer

Test-Driven-Development: Schreiben Sie einen `CustomerRestControllerTest`, der die 
Abfrage (Lesen) eines einzelnen Kunden anhand dessen Id durchführt.

Dieser Test soll die JSON Antwort zumindest zu Teilen auf Korrektheit prüfen.

Da es den Endpunkt hierfür noch nicht gibt, wird dieser Test vorerst fehlschlagen.

## b) Erweiterung CustomerService

Erweitern Sie den `CustomerService` um eine `getCustomer(long id)` Methode, 
die ebenfalls eine Exception (welche wohl?) wirft, wenn der Kunde nicht existiert.

## c) Erweiterung CustomerRestController

Erweitern Sie den `CustomerRestController` um einen Endpunkt für die neue 
Service-Methode.

Der TDD Test sollte nun erfolgreich durchlaufen.

## d) CustomerRestControllerTest - createCustomer

Ergänzen Sie den `CustomerRestControllerTest` um einen Testfall, der die 
korrekte Anlage eines neuen Kunden prüft.

Die Prüfung kann nur aus Abfrage des richtigen Statuswerts (201-CREATED) bestehen.

## e) OrderRestControllerTest

Schreiben Sie einen `OrderRestControllerTest` für placeOrder(), der das erfolgreiche 
Anlegen einer Bestellung inklusive Prüfung auf korrekten Gesamtpreis und Name des 
Empfängers validiert.

Versuchen Sie diesen Test nicht als `@SpringBootTest`, sondern mit der Variante `@WebMvcTest`
zu schreiben, sodass nur ein Teil des Kontexts gestartet werden muss (schneller).

Da wir ja einen vollständigen Integrationstest möchten, muss also trotz des Slicings auf Web-Komponenten
hin auch zusätzlich noch die entsprechenden Services und Repositories angelegt werden. 
Dazu brauchen Sie einige andere Annotationen, wie z.B.
* `@Import`
* `@AutoConfigureDataJpa`
* `@EnableJpaRepositories`

Optional kann noch ein Negativtest geschrieben werden, der das Verhalten bei 
Angabe einer falschen Kunden-Telefonnummer oder Produkt-Id prüft.



