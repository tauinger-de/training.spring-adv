# Übungen

## 060 - Konfiguration

### A) Konfiguration via application.properties

Verändern Sie den OrderService, sodass die erwartete Lieferzeit in Minuten sowie die 
Rabattierung je Wochentag in einer externen Konfigurationsquelle hinterlegt werden kann.

*Hinweis: Hierfür gibt es zwei Möglichkeiten (mittels `@Value` oder `@ConfigurationProperties`, 
letztere ist schwerer aufgrund des noch zu implementierenden Converters, siehe Musterlösung)

### B) Konfiguration von außen

Starten Sie Ihre Anwendung auf eine Art und Weise, dass die Lieferzeit in Minuten nicht 
aus den `application.properties` genutzt wird, sondern von außen durch einen anderen Wert 
ersetzt wird.

Hierfür können Sie eine Umgebungsvariable, ein VM System Property oder ein Programmargument nutzen.

Wird der erwartete Wert ausgegeben?

### C) CustomerSetup konfigurierbar machen

Fügen Sie das Property `app.customer.perform-setup = true` zu Ihrer Konfiguration hinzu.

Schreiben Sie die Klasse `CustomerSetup` so um, dass sie nur als Bean existiert (Stichwort `@Conditional...` Annotation), 
wenn das Property den Wert `true` enthält.

### D) Begrüßung

Legen sie die Datei `/src/main/resources/greeting.txt` an.

Erzeugen Sie eine Bean vom Typ `String` und Name "greeting", wenn diese Datei existiert. 
Die Bean soll als Wert den Inhalt der Datei haben.

Injizieren Sie die Bean in den `OrderService`.

Geben Sie den Begrüßungstext per `System.out.println()` zu Beginn der Bestellung aus, 
falls die Begrüßung nicht leer ist.

## 070 - Transaktionen

### a) Customer um "orderCount" erweitern

Fügen Sie einen Counter (Zählwert) im Customer namens `orderCount` ein – 
die Getter-Methode dafür bitte nicht vergessen.

### b) CustomerService erweitern

Fügen Sie eine neue Methode 
`com.example.pizza.customer.CustomerService#increaseOrderCount()` ein, die in einer 
neuen Transaktion den Zähler hochsetzt und den neuen Wert persistiert.

### c) OrderService erweitern

Rufen Sie die neue Methode im `CustomerService` aus `OrderService#placeOrder()` auf, 
und zwar gleich, nachdem der Customer geladen wurde.

Außerdem soll die `placeOrder()` Methode auch in einer Transaktion ablaufen.

### d) Test

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

