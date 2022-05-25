# Übungen

## 020 - Parent-POM, Starter

### a) Analyse spring-boot-starter JAR

Welche Klassen sind in der spring-boot-starter JAR Datei enthalten?

Und welchen Sinn hat dieses JAR?

### b) Eigenes Projekt erstellen

Gehen Sie bitte auf die Seite https://start.spring.io/ und erstellen Sie mit dem 
Spring Initializr ein neues Projekt.

Alle vorgeschlagenen Einstellungen übernehmen, bis auf:
* GroupId "com.example"
* Artifact "pizza"
* Java Version: 11

Dann **Generate** klicken und geladene ZIP Datei entpacken und Inhalt untersuchen. 

PS Ggf. unterstützt Ihre IDE dies direkt (z.B. in Intellij: File > New > Project...)

### c) Hauptprojekt Branch wechseln

In der vorherigen Lektion hatten wir das Beispielprojekt im Branch 
"lesson-010" ausgecheckt. 

Wechseln Sie nun dort auf den Branch "lesson-020". Dadurch aktualisiert sich das Projekt auf einen Stand der (ungefähr) 
dem entspricht, was Sie eben mit Spring Initializr erstellt haben.

````shell
$ git checkout --force lesson-020
````

### d) Anwendung starten

Starten Sie die Anwendung von der Kommandozeile

````shell
$ mvnw clean spring-boot:run
````

Beenden Sie die Anwendung wieder (STRG+C) und starten Sie dann die Anwendung
aus Ihrer Entwicklungsumgebung.

### e) Unterschiede im Branch verstehen

Zeigen Sie sich mithilfe Ihrer Entwicklungsumgebung die Unterschiede zwischen Branch "lesson-010" 
und "lesson-020" an, um die Änderungen zu verstehen.

### f) Untersuchung der gebauten JAR

Führen Sie einen Maven Build aus (`mvnw clean package`) und untersuchen Sie 
die resultierende JAR Datei im `/target` Verzeichnis (z.B. mit 7zip).


## 030 - Bean Definitions und Injection

### a) Erstellung der Address Klasse

Erstellen Sie bitte eine POJO Model Klasse `com.example.pizza.customer.Address` 
mit den folgenden Feldern:
````
String street;
String postalCode;
String city;
````
Sie können auch die Klasse aus der Musterlösung (Branch 030) kopieren (z.B.
durch das Compare Tool Ihrer IDE).

### b) Erzeugung von Address Beans

Erstellen Sie bitte eine Bean Factory Klasse (mittels `@Configuration`) 
`com.example.pizza.customer.AddressSetup`, die zwei benannte Beans vom Typ 
`Address` erzeugt (mittels `@Bean` annotierten Methoden).

### c) Erstellung der Customer Klasse

Erstellen Sie bitte eine POJO Model Klasse `com.example.pizza.customer.Customer` 
mit den folgenden Feldern:
````
Long id;
String fullName;
Address address;
String phoneNumber;
````
Sie können auch die Klasse aus der Musterlösung (Branch 030) kopieren (z.B.
durch das Compare Tool Ihrer IDE).

### d) Erstellung der CustomerService Klasse

Erstellen Sie bitte eine `@Service` annotierte Klasse 
`com.example.pizza.customer.CustomerService` mit zwei leeren Methoden:
````
public Customer createCustomer(Customer customer)
public Iterable<Customer> getAllCustomers()
````

Gerne können Sie diese (wie auch immer) mit Code füllen.

### e) Erstellung einer CustomerSetup Klasse

Erstellen Sie bitte eine `CustomerSetup` Klasse, die: 

* mittels `@PostConstruct` 
* und Nutzung der mittlerweile im Kontext existierenden `Address` Beans
* zwei oder mehr `Customer` Instanzen anlegt 
* und für diese die `createCustomer()` Methode des injizierten `CustomerService` aufruft


## 040 - Testing

### a) Test-Driven-Development zur Abfrage eines Produkts

Erstellen Sie eine `ProductServiceTest` Klasse mit der Testmethode 
`getProduct()`. 

In diesem Test soll die Abfrage eines Produkts anhand der Methode `ProductService.getProduct()`
getestet werden.

Der Test soll mittels der `ProductService.createProduct()` 
Methode die benötigten Testdaten selbst anlegen.

Hinweise:
* Im Sinne des TDD legen wir den Test vor der tatsächlichen Implementierung an -- und können diesen zum 
Debuggen des neuen Source-Codes nutzen
* Anfänglich wird der Test natürlich fehlschlagen
* In den unten folgenden Übungen werden weitere Code-Teile erstellt – bitte machen Sie pro Übung nicht zu viel

### b) Interface ProductRepository

Legen Sie ein leeres `com.example.pizza.product.ProductRepository` Interface 
an und injizieren Sie dieses in den `ProductService`.

### c) Nutzung ProductRepository

Füllen Sie die Geschäftslogik im `ProductService` mit Code, der das `ProductRepository` nutzt.

Dafür müssen Sie neue Methoden in dem `ProductRepository` anlegen.

### d) Implementation InMemoryProductRepository

Erstellen Sie eine `ProductRepository` Implementation mit Namen 
`InMemoryProductRepository`, die intern mit einer `HashMap` zur Datenhaltung 
arbeitet.

Hinweis: Ihr Test soll jetzt erfolgreich durchlaufen.

### e) Bestehenden Testfall aktivieren

Aktivieren Sie den Testfall 
`ProductServiceTest.createProduct_failsForDuplicateProductId()` und schauen 
Sie, dass dieser erfolgreich läuft.

### f) Test-Driven-Development von getTotalPrice()

Schauen Sie sich die Signatur der Service-Methode `ProductService.getTotalPrice()` 
an – welchen Wert soll diese wohl auf Basis der Eingabeparameter zurückgeben?

Vervollständigen Sie den Testfall `ProductServiceTest_WithMocks.getTotalPrice()` 
sodass dieser mithilfe eines gemockten Repositories die Geschäftslogik zur 
Preisberechnung prüft – dieser Test wird vorerst fehlschlagen, da die Service-Methode 
ja noch leer ist.

Implementieren Sie dann die Service-Methode.


## 050 - Spring Data JPA

### a) Test-Driven-Development der Speicherung eines Kunden

Schreiben Sie einen Testfall, der die erfolgreiche Persistierung einer `Customer` Entität
(inkl. Address) mittels des `CustomerService` prüft.

Dieser Testfall wird vorerst fehlschlagen – die nachfolgenden Übungen liefern dann 
den fehlenden Code.

### b) Pom.xml erweitern

Fügen Sie den JPA Starter und die H2 Dependency der pom.xml hinzu.

Ggf. müssen Sie die Maven-Projektkonfiguration in Ihrer IDE aktualisieren.

### c) Entities & Repositories

Versehen Sie die Entitäten `Order`, `Product`, `Customer` und `Address` mit den benötigten 
Annotationen. 

Bitte folgende Spezialfälle berücksichtigen:
* Die Bestellungen sollen in der Tabelle "orders" hinterlegt sein 
(Warum geht wohl nur "order" nicht?)
* Ein Kunde hat seine Telefonnummer in der Spalte "phone" hinterlegt 
 
Legen Sie dann die benötigten Repositories für `Order`, `Product` und `Customer` an.

### d) Repositories nutzen

Verändern Sie alle drei Services so, dass diese nun mit den Repositories arbeiten.

### e) Custom Queries

Schreiben Sie eine Methode in das `CustomerRepository`, welches einen oder mehrere 
Kunden anhand des Präfixes ihrer Telefonnummer findet.

Erstellen Sie eine Klasse `CustomerRepositoryTest`, in der diese Methode getestet wird.

Beispiel: Bei Suche nach "123" werden alle Kunden gefunden, deren Telefonnummer 
mit "123" anfängt


## 060 - Konfiguration

### a) placeOrder Test

Erstellen Sie einen einfachen placeOrder() Test in der Klasse OrderServiceTest, der einfach
nur eine Bestellung auslöst -- ohne weitere Checks.

Hiermit können Sie den Bestell-Code ausführen und die Konfiguration weiter unten sehen.

### b) OrderService konfigurierbar machen

Verändern Sie den OrderService, sodass die erwartete Lieferzeit in Minuten sowie die
Rabattierung je Wochentag in einer externen Konfigurationsquelle hinterlegt werden kann.

*Hinweis: Hierfür gibt es zwei Möglichkeiten (mittels `@Value` oder `@ConfigurationProperties`,
letztere ist schwerer aufgrund des noch zu implementierenden Converters, siehe Musterlösung)

### c) Konfiguration via application.properties

Setzen Sie Werte für die Konfiguration der vorherigen Seite in der `application.properties`.

Geben Sie die aktuelle Konfiguration im OrderService in einer `@PostConstruct` Methode aus 
(System.out).

Starten Sie die Anwendung und prüfen Sie die tatsächlich vorliegende Konfiguration -- 
wird der Wert aus der `application.properties` genutzt?

### d) Konfiguration von außen

Starten Sie Ihre Anwendung auf eine Art und Weise, dass die Lieferzeit in Minuten nicht 
aus den `application.properties` genutzt wird, sondern von außen durch einen anderen Wert 
ersetzt wird.

Hierfür können Sie eine Umgebungsvariable, ein VM System Property oder ein Programmargument nutzen.

Wird der erwartete Wert ausgegeben?

### e) CustomerSetup konfigurierbar machen

Fügen Sie das Property `app.customer.perform-setup = true` zu Ihrer Konfiguration hinzu.

Schreiben Sie die Klasse `CustomerSetup` so um, dass sie nur als Bean existiert (Stichwort `@Conditional...` Annotation), 
wenn das Property den Wert "true" enthält.

### f) Begrüßung

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

Dies ist ein Zähler für die Anzahl **versuchter** (nicht unbedingt erfolgreicher) Bestellungen.

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

