# Übung zu Kapitel "040 - Testing"

## a) Test-Driven-Development zur Abfrage eines Produkts

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

## b) Interface ProductRepository

Legen Sie ein leeres `com.example.pizza.product.ProductRepository` Interface 
an und injizieren Sie dieses in den `ProductService`.

## c) Nutzung ProductRepository

Füllen Sie die Geschäftslogik im `ProductService` mit Code, der das `ProductRepository` nutzt.

Dafür müssen Sie neue Methoden in dem `ProductRepository` anlegen.

## d) Implementation InMemoryProductRepository

Erstellen Sie eine `ProductRepository` Implementation mit Namen 
`InMemoryProductRepository`, die intern mit einer `HashMap` zur Datenhaltung 
arbeitet.

Hinweis: Ihr Test soll jetzt erfolgreich durchlaufen.

## e) Bestehenden Testfall aktivieren

Aktivieren Sie den Testfall 
`ProductServiceTest.createProduct_failsForDuplicateProductId()` und schauen 
Sie, dass dieser erfolgreich läuft.

## f) Test-Driven-Development von getTotalPrice()

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

Fügen sie den JPA Starter und die H2 Dependency der pom.xml hinzu.

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
