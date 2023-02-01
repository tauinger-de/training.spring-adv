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

Wie kann dieser Test implementiert werden, wenn man `@SpringBootTest` nicht nutzen möchte?

