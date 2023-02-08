## Übungen zum Kapitel "150 - Integration"

### a) pom.xml

Fügen Sie die Abhängigkeit für "spring-integration-file" der `pom.xml` hinzu

### b) FileIntegrationConfig

Erstellen Sie eine `FileIntegrationConfig` Klasse und legen Sie dort drei Beans an:
1. für den MessageChannel
2. für den Nachrichten-produzierenden Endpoint
3. für den Nachrichten-verarbeitenden Endpoint -- dieser kann erstmal nur eine Ausgabe
   per Logger durchführen, wenn eine Nachricht mit einer `File` Payload eintrifft

### c) Betrieb

Starten Sie die Anwendung.

Kopieren Sie die Datei `/data/template.json` in die Datei `/data/order1.json`, z.B.
via Kommandozeile mit `copy` (Windows) or `cp` (Linux/macOS).

Erfolgt die entsprechende Ausgabe? Sie können auch einen Breakpoint setzen und
den Empfang der Nachricht debuggen.

### d) OrderService aufrufen

Erweitern Sie den `ServiceActivator`, sodass ein Aufruf auf den `OrderService` erfolgt.


