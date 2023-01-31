# Übung zu Kapitel "020 - Parent-POM, Starter"

## a) die Beispielanwendung

Schauen Sie sich den Source Code an der Anwendung hier im Projekt an.

## b) Anwendung starten

Starten Sie die Anwendung von der Kommandozeile

````shell
$ mvnw clean spring-boot:run
````

Beenden Sie die Anwendung wieder (STRG+C) und starten Sie dann die Anwendung
aus Ihrer Entwicklungsumgebung.

Was passiert wohl beim Starten der Anwendung?

Warum erscheint die Ausgabe "Los geht's" so früh und die Ausgabe "Welcome" so spät?

## c) Analyse spring-boot-starter JAR

Welche Klassen sind in der spring-boot-starter JAR Datei enthalten?

Und welchen Sinn hat dieses JAR?

## d) Untersuchung der gebauten JAR

Führen Sie einen Maven Build aus (`mvnw clean package`) und untersuchen Sie
die resultierende JAR Datei im `/target` Verzeichnis (z.B. mit 7zip).

## e) Optional: ein eigenes Projekt erstellen

Gehen Sie auf die Seite https://start.spring.io/ und erstellen Sie mit dem 
Spring Initializr ein neues Projekt.

Gerne können Sie auch testweise Dependencies hinzufügen (z.B. "Web").

Dann **Generate** klicken und geladene ZIP Datei entpacken und Inhalt untersuchen.
Oder im Browser die generierten Dateien betrachten (und kopieren).

PS Ggf. unterstützt Ihre IDE dies direkt (z.B. in Intellij: File > New > Project...)

