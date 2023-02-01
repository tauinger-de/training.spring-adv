# Übung zu Kapitel "050 - Spring Data JPA"

## a) Test-Driven-Development der Speicherung eines Kunden

Schreiben Sie einen Testfall, der die erfolgreiche Persistierung einer `Customer` Entität
(inkl. Address) mittels des `CustomerService` prüft.

Dieser Testfall wird vorerst fehlschlagen – die nachfolgenden Übungen liefern dann 
den fehlenden Code.

## b) Pom.xml erweitern

Fügen sie den JPA Starter und die H2 Dependency der pom.xml hinzu.

Ggf. müssen Sie die Maven-Projektkonfiguration in Ihrer IDE aktualisieren.

## c) Entities & Repositories

Versehen Sie die Entitäten `Order`, `Product`, `Customer` und `Address` mit den benötigten 
Annotationen. 

Bitte folgende Spezialfälle berücksichtigen:
* Die Bestellungen sollen in der Tabelle "orders" hinterlegt sein 
(Warum geht wohl nur "order" nicht?)
* Ein Kunde hat seine Telefonnummer in der Spalte "phone" hinterlegt 
 
Legen Sie dann die benötigten Repositories für `Order`, `Product` und `Customer` an.

## d) Repositories nutzen

Verändern Sie alle drei Services so, dass diese nun mit den Repositories arbeiten.

## e) Custom Queries

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
wenn das Property den Wert “true” enthält.

### f) Begrüßung

Legen sie die Datei `/src/main/resources/greeting.txt` an.

Erzeugen Sie eine Bean vom Typ `String` und Name "greeting", wenn diese Datei existiert. 
Die Bean soll als Wert den Inhalt der Datei haben.

Injizieren Sie die Bean in den `OrderService`.

Geben Sie den Begrüßungstext per `System.out.println()` zu Beginn der Bestellung aus, 
falls die Begrüßung nicht leer ist.
