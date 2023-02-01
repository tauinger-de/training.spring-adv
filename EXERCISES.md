# Übung zu Kapitel "060 - Konfiguration"

## a) placeOrder Test

Erstellen Sie einen einfachen placeOrder() Test in der Klasse OrderServiceTest, der einfach
nur eine Bestellung auslöst -- ohne weitere Checks.

Hiermit können Sie den Bestell-Code ausführen und die Konfiguration weiter unten sehen.

## b) OrderService konfigurierbar machen

Verändern Sie den OrderService, sodass die erwartete Lieferzeit in Minuten sowie die
Rabattierung je Wochentag in einer externen Konfigurationsquelle hinterlegt werden kann.

*Hinweis: Hierfür gibt es zwei Möglichkeiten (mittels `@Value` oder `@ConfigurationProperties`,
letztere ist schwerer aufgrund des noch zu implementierenden Converters, siehe Musterlösung)

## c) Konfiguration via application.properties

Setzen Sie Werte für die Konfiguration der vorherigen Seite in der `application.properties`.

Geben Sie die aktuelle Konfiguration im OrderService in einer `@PostConstruct` Methode aus 
(System.out).

Starten Sie die Anwendung und prüfen Sie die tatsächlich vorliegende Konfiguration -- 
wird der Wert aus der `application.properties` genutzt?

## d) Konfiguration von außen

Starten Sie Ihre Anwendung auf eine Art und Weise, dass die Lieferzeit in Minuten nicht 
aus den `application.properties` genutzt wird, sondern von außen durch einen anderen Wert 
ersetzt wird.

Hierfür können Sie eine Umgebungsvariable, ein VM System Property oder ein Programmargument nutzen.

Wird der erwartete Wert ausgegeben?

## e) CustomerSetup konfigurierbar machen

Fügen Sie das Property `app.customer.perform-setup = true` zu Ihrer Konfiguration hinzu.

Schreiben Sie die Klasse `CustomerSetup` so um, dass sie nur als Bean existiert (Stichwort `@Conditional...` Annotation), 
wenn das Property den Wert “true” enthält.

## f) Begrüßung

Legen sie die Datei `/src/main/resources/greeting.txt` an.

Erzeugen Sie eine Bean vom Typ `String` und Name "greeting", wenn diese Datei existiert. 
Die Bean soll als Wert den Inhalt der Datei haben.

Injizieren Sie die Bean in den `OrderService`.

Geben Sie den Begrüßungstext per `System.out.println()` zu Beginn der Bestellung aus, 
falls die Begrüßung nicht leer ist.
