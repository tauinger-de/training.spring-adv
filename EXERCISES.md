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


## 100 - Profile, Logging und Monitoring

### a) "Nur Customer” Profil

Markieren Sie alle relevanten Beans mit `@Profile` Annotationen, sodass bei Aufruf 
der Anwendung mit dem Profil "customer" nur die Funktionalität des Customer-Moduls 
zur Verfügung steht.

Gerne können Sie auch die Übung so weit treiben, dass die folgenden Profile zur Auswahl 
stehen und die Anwendung bei jedem der Profile lauffähig ist:
* "product"
* "customer"
* "order"
* kein Profil gesetzt

### b) “Dev” Profil

Legen Sie eine Konfigurationsdatei für das Profil "dev" an, bei dem vermehrt 
Logging Ausgaben generiert werden, z.B. Logging-Level aller "pizza" Klassen auf DEBUG.

### c) System.out durch Logging

Ersetzen Sie Ausgaben in der Anwendung, die bisher per System.out.println() erfolgt sind, 
durch Logging Ausgaben.

### d) Monitoring aktivieren

Aktivieren Sie Monitoring für die Anwendung, sodass folgende Endpunkte im Browser abrufbar sind:
* health
* info
* env