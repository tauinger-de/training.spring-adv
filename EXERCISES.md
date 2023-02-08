# Übungen zum aktuellen Kapitel "100 - Profile, Logging und Monitoring"

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


## Übungen zum nächsten Kapitel "110 - AOP"

### a) Logging Aspect

Erstellen sie eine leere Klasse `com.example.pizza.aop.LoggingAspect`.

Legen Sie darin eine statische Variable für einen Logger an.

### b) Erste Aspect Methode

Programmieren Sie eine Methode `logCreate(JoinPoint jp)`, die einen per Logger protokolliert, 
wenn über eine Methode ein Objekt angelegt werden soll, sprich, wenn in
einem Service eine `createXyz()` Methode aufgerufen wird.

Die Ausgabe soll das anzulegende Objekt enthalten.

Ergänzen Sie diese Methode mit einer entsprechenden Advice-Annotation, sodass der Aspect für 
alle mit "create" beginnenden Methode im `com.example.pizza` Package ausgeführt wird.

### c) Zweite Aspect Methode

Programmieren Sie eine weitere Aspect-Methode `logExecutionTime(ProceedingJoinPoint jp)`, 
die die Ausführungszeit einer Methode misst und diese per Logger ausgibt.

Ergänzen Sie diese Methode mit einer entsprechenden Advice-Annotation, sodass der 
Aspect für Methoden ausgeführt wird, die mit `@LogExecutionTime` annotiert sind.

Hinweis: `@LogExecutionTime` ist von Ihnen selber als Annotation anzulegen.

Bonus: Wie kann erreicht werden, dass nicht der Logger des Aspects, sondern der Logger 
der Klasse genommen wird, in dem die mit `@LogExecutionTime` annotierte Methode enthalten ist?

