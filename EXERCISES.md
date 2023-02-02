# Übung zu Kapitel "070 - Transaktionen"

## a) Customer um "orderCount" erweitern

Fügen Sie einen Counter (Zählwert) im Customer namens `orderCount` ein – 
die Getter-Methode dafür bitte nicht vergessen.

Dies ist ein Zähler für die Anzahl **versuchter** (nicht unbedingt erfolgreicher) Bestellungen.

## b) CustomerService erweitern

Fügen Sie eine neue Methode 
`com.example.pizza.customer.CustomerService#increaseOrderCount()` ein, die in einer 
neuen Transaktion den Zähler hochsetzt und den neuen Wert persistiert.

## c) OrderService erweitern

Rufen Sie die neue Methode im `CustomerService` aus `OrderService#placeOrder()` auf,  
und zwar gleich, nachdem der Customer geladen wurde.

Außerdem soll die `placeOrder()` Methode auch in einer Transaktion ablaufen.

## d) Test

Schreiben Sie einen Test 
`com.example.pizza.order.OrderServiceTest#placeOrder_customerOrderCountIncreasesDespiteTransactionFail()` 
der einen ungültigen Bestellvorgang auslöst und dann prüft, dass dennoch 
der Zähler der Customer Entität erhöht wurde.

## 080 - RESTful API

### a) Insomnia Rest Client

Bei Bedarf können Sie den "Insomnia Core" Rest Client installieren: https://insomnia.rest/

Im Branch 080 ist ein exportierter Insomnia Workspace vorhanden, den Sie in das Tool importieren können. 
Oder Sie legen die wenigen Requests selbst an.

Alternativ können Sie zum Testen der Endpunkte natürlich auch Postman oder den 
Kommandozeilenbefehl curl nehmen.

### b) pom.xml erweitern

Fügen Sie den notwendigen Starter der `pom.xml` hinzu

### c) OrderRestController

Erstellen Sie einen API-Endpunkt `/orders/greeting` der für ein GET 
eine Begrüßung als String zurückgibt.

Testen Sie die URL in Ihrem Browser: http://localhost:8080/orders/greeting

### d) CustomerRestController

Erstellen Sie die folgenden API-Endpunkte:

* `GET /customers`, der alle Kunden zurückgibt
* `POST /customers`, der einen neuen Kunden anlegt

### e) ProductRestController

Erstellen Sie die folgenden API-Endpunkte:

* `GET /products`, der alle Produkte zurückgibt

### f) Erweiterung OrderRestController

Erstellen Sie zusätzlich die folgenden API-Endpunkte:

* `GET /orders`, der alle Bestellungen zurückgibt
* `POST /orders`, durch den eine neue Bestellung aufgegeben werden kann. Die Bestellung ist durch 
folgenden JSON Inhalt definiert:
````json
{
    "phoneNumber": "123-4567",
    "itemQuantities": {
        "S-02": 1,
        "P-10": 2,
        "P-12": 1
    }
}
````


## 090 - End-to-end Testing

### a) CustomerRestControllerTest - getCustomer

Test-Driven-Development: Schreiben Sie einen `CustomerRestControllerTest`, der die 
Abfrage (Lesen) eines einzelnen Kunden anhand dessen Id durchführt.

Dieser Test soll die JSON Antwort zumindest zu Teilen auf Korrektheit prüfen.

Da es den Endpunkt hierfür noch nicht gibt, wird dieser Test vorerst fehlschlagen.

### b) Erweiterung CustomerService

Erweitern Sie den `CustomerService` um eine `getCustomer(long id)` Methode, 
die ebenfalls eine Exception (welche wohl?) wirft, wenn der Kunde nicht existiert.

### c) Erweiterung CustomerRestController

Erweitern Sie den `CustomerRestController` um einen Endpunkt für die neue 
Service-Methode.

Der TDD Test sollte nun erfolgreich durchlaufen.

### d) CustomerRestControllerTest - createCustomer

Ergänzen Sie den `CustomerRestControllerTest` um einen Testfall, der die 
korrekte Anlage eines neuen Kunden prüft.

Die Prüfung kann nur aus Abfrage des richtigen Statuswerts (201-CREATED) bestehen.

### e) OrderRestControllerTest

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