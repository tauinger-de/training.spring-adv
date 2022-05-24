# Übungen

## 020 - Parent-POM, Starter

### a) Analyse spring-boot-starter JAR

Welche Klassen sind in der spring-boot-starter JAR Datei enthalten?

Und welchen Sinn hat dieses JAR?

### b) Eigenes Projekt erstellen

Gehen Sie bitte auf die Seite https://start.spring.io/ und erstellen Sie mit dem 
Spring Initializr ein neues Projekt.

Alle vorgeschlagenen Einstellungen übernehmen, bis auf:
* Artifact “pizza”	
* Dependency “Spring Web” hinzufügen (zu finden via „web“ Keyword)
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
