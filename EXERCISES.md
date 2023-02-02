# Übung zu Kapitel "080 - RESTful API"

## a) Insomnia Rest Client

Bei Bedarf können Sie den "Insomnia Core" Rest Client installieren: https://insomnia.rest/

Im Branch 080 ist ein exportierter Insomnia Workspace vorhanden, den Sie in das Tool importieren können. 
Oder Sie legen die wenigen Requests selbst an.

Alternativ können Sie zum Testen der Endpunkte natürlich auch Postman oder den 
Kommandozeilenbefehl curl nehmen.

## b) pom.xml erweitern

Fügen Sie den notwendigen Starter der `pom.xml` hinzu

## c) OrderRestController

Erstellen Sie einen API-Endpunkt `/orders/greeting` der für ein GET 
eine Begrüßung als String zurückgibt.

Testen Sie die URL in Ihrem Browser: http://localhost:8080/orders/greeting

## d) CustomerRestController

Erstellen Sie die folgenden API-Endpunkte:

* `GET /customers`, der alle Kunden zurückgibt
* `POST /customers`, der einen neuen Kunden anlegt

## e) ProductRestController

Erstellen Sie die folgenden API-Endpunkte:

* `GET /products`, der alle Produkte zurückgibt

## f) Erweiterung OrderRestController

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

