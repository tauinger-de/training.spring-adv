# Übungen

## 020 - Parent-POM, Starter

### a) Analyse spring-boot-starter JAR

Welche Klassen sind in der spring-boot-starter JAR Datei enthalten?

Und welchen Sinn hat dieses JAR?

### b) Eigenes Projekt erstellen

Gehen Sie bitte auf die Seite https://start.spring.io/ und erstellen Sie mit dem 
Spring Initializr ein neues Projekt.

Alle vorgeschlagenen Einstellungen übernehmen, bis auf:
* GroupId "com.example"
* Artifact "pizza"
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


## 030 - Bean Definitions und Injection

### a) Erstellung der Address Klasse

Erstellen Sie bitte eine POJO Model Klasse `com.example.pizza.customer.Address` 
mit den folgenden Feldern:
````
String street;
String postalCode;
String city;
````
Sie können auch die Klasse aus der Musterlösung (Branch 030) kopieren (z.B.
durch das Compare Tool Ihrer IDE).

### b) Erzeugung von Address Beans

Erstellen Sie bitte eine Bean Factory Klasse (mittels `@Configuration`) 
`com.example.pizza.customer.AddressSetup`, die zwei benannte Beans vom Typ 
`Address` erzeugt (mittels `@Bean` annotierten Methoden).

### c) Erstellung der Customer Klasse

Erstellen Sie bitte eine POJO Model Klasse `com.example.pizza.customer.Customer` 
mit den folgenden Feldern:
````
Long id;
String fullName;
Address address;
String phoneNumber;
````
Sie können auch die Klasse aus der Musterlösung (Branch 030) kopieren (z.B.
durch das Compare Tool Ihrer IDE).

### d) Erstellung der CustomerService Klasse

Erstellen Sie bitte eine `@Service` annotierte Klasse 
`com.example.pizza.customer.CustomerService` mit zwei leeren Methoden:
````
public Customer createCustomer(Customer customer)
public Iterable<Customer> getAllCustomers()
````

Gerne können Sie diese (wie auch immer) mit Code füllen.

### e) Erstellung einer CustomerSetup Klasse

Erstellen Sie bitte eine `CustomerSetup` Klasse, die: 

* mittels `@PostConstruct` 
* und Nutzung der mittlerweile im Kontext existierenden `Address` Beans
* zwei oder mehr `Customer` Instanzen anlegt 
* und für diese die `createCustomer()` Methode des injizierten `CustomerService` aufruft
