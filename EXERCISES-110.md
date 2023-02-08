# Übungen zum Kapitel "110 - AOP"

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

