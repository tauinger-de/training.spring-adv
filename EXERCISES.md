# Übung zu Kapitel "070 - Transaktionen"

## a) Customer um "orderCount" erweitern

Fügen Sie einen Counter (Zählwert) im Customer namens `orderCount` ein – 
die Getter-Methode dafür bitte nicht vergessen.

Dies ist ein Zähler für die Anzahl **versuchter** (nicht unbedingt erfolgreicher) Bestellungen.

## b) CustomerService erweitern

Fügen Sie eine neue Methode 
`com.example.pizza.customer.CustomerService#increaseOrderCount()` ein, die in einer 
neuen Transaktion den Zähler hochsetzt und den neuen Wert persistiert.

## c) OrderService erweitern

Rufen Sie die neue Methode im `CustomerService` aus `OrderService#placeOrder()` auf,  
und zwar gleich, nachdem der Customer geladen wurde.

Außerdem soll die `placeOrder()` Methode auch in einer Transaktion ablaufen.

## d) Test

Schreiben Sie einen Test 
`com.example.pizza.order.OrderServiceTest#placeOrder_customerOrderCountIncreasesDespiteTransactionFail()` 
der einen ungültigen Bestellvorgang auslöst und dann prüft, dass dennoch 
der Zähler der Customer Entität erhöht wurde.

