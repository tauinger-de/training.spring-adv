# Übungen

## 060 - Konfiguration

### A) Konfiguration via application.properties

Verändern Sie den OrderService, sodass die erwartete Lieferzeit in Minuten sowie die 
Rabattierung je Wochentag in einer externen Konfigurationsquelle hinterlegt werden kann.

*Hinweis: Hierfür gibt es zwei Möglichkeiten (mittels `@Value` oder `@ConfigurationProperties`, 
letztere ist schwerer aufgrund des noch zu implementierenden Converters, siehe Musterlösung)

### B) Konfiguration von außen

Starten Sie Ihre Anwendung auf eine Art und Weise, dass die Lieferzeit in Minuten nicht 
aus den `application.properties` genutzt wird, sondern von außen durch einen anderen Wert 
ersetzt wird.

Hierfür können Sie eine Umgebungsvariable, ein VM System Property oder ein Programmargument nutzen.

Wird der erwartete Wert ausgegeben?

### C) CustomerSetup konfigurierbar machen

Fügen Sie das Property `app.customer.perform-setup = true` zu Ihrer Konfiguration hinzu.

Schreiben Sie die Klasse `CustomerSetup` so um, dass sie nur als Bean existiert (Stichwort `@Conditional...` Annotation), 
wenn das Property den Wert “true” enthält.

### D) Begrüßung

Legen sie die Datei `/src/main/resources/greeting.txt` an.

Erzeugen Sie eine Bean vom Typ `String` und Name "greeting", wenn diese Datei existiert. 
Die Bean soll als Wert den Inhalt der Datei haben.

Injizieren Sie die Bean in den `OrderService`.

Geben Sie den Begrüßungstext per `System.out.println()` zu Beginn der Bestellung aus, 
falls die Begrüßung nicht leer ist.

## 070 - Transaktionen

### Customer um “orderCount” erweitern

Fügen Sie einen Counter (Zählwert) im Customer namens `orderCount` ein – 
die Getter-Methode dafür bitte nicht vergessen.

### CustomerService erweitern

Fügen Sie eine neue Methode 
`com.example.pizza.customer.CustomerService#increaseOrderCount()` ein, die in einer 
neuen Transaktion den Zähler hochsetzt und den neuen Wert persistiert.

### OrderService erweitern

Rufen Sie die neue Methode im `CustomerService` aus `OrderService#placeOrder()` auf -- 
und zwar gleich, nachdem der Customer geladen wurde.

Außerdem soll die `placeOrder()` Methode auch in einer Transaktion ablaufen.

### Test

Schreiben Sie einen Test 
`com.example.pizza.order.OrderServiceTest#placeOrder_customerOrderCountIncreasesDespiteTransactionFail()` 
der einen ungültigen Bestellvorgang auslöst und dann prüft, dass dennoch 
der Zähler der Customer Entität erhöht wurde.

