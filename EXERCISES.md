# Übung zu Kapitel "100 - Profile, Logging und Monitoring"

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