# Übung zu Kapitel "050 - Spring Data JPA"

## a) Test-Driven-Development der Speicherung eines Kunden

Schreiben Sie einen Testfall, der die erfolgreiche Persistierung einer `Customer` Entität
(inkl. Address) mittels des `CustomerService` prüft.

Dieser Testfall wird vorerst fehlschlagen – die nachfolgenden Übungen liefern dann 
den fehlenden Code.

## b) Pom.xml erweitern

Fügen Sie den JPA Starter und die H2 Dependency der pom.xml hinzu.

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
