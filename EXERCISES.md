# Übung zu Kapitel "030 - Bean Definitions und Injection"

## a) Erstellung der Address Klasse

Erstellen Sie bitte eine POJO Model Klasse `com.example.pizza.customer.Address` 
mit den folgenden Feldern:
````
String street;
String postalCode;
String city;
````
Sie können diese Klasse aus der Musterlösung (Branch 030) kopieren (z.B.
durch das Compare Tool Ihrer IDE).

## b) Erzeugung von Address Beans

Erstellen Sie bitte eine Bean Factory Klasse (mittels `@Configuration`) 
`com.example.pizza.customer.AddressSetup`, die zwei benannte Beans vom Typ 
`Address` erzeugt (mittels `@Bean` annotierten Methoden).

## c) Erstellung der Customer Klasse

Erstellen Sie bitte eine POJO Model Klasse `com.example.pizza.customer.Customer` 
mit den folgenden Feldern:
````
Long id;
String fullName;
Address address;
String phoneNumber;
````
Sie können auch diese Klasse aus der Musterlösung (Branch 030) kopieren (z.B.
durch das Compare Tool Ihrer IDE).

## d) Erstellung der CustomerService Klasse

Erstellen Sie bitte eine `@Service` annotierte Klasse 
`com.example.pizza.customer.CustomerService` mit zwei leeren Methoden:
````
public Customer createCustomer(Customer customer)
public Iterable<Customer> getAllCustomers()
````

Gerne können Sie diese (wie auch immer) mit Code füllen.

## e) Erstellung einer CustomerSetup Klasse

Erstellen Sie bitte eine `CustomerSetup` Klasse, die: 

* mittels `@PostConstruct` 
* und Nutzung der mittlerweile im Kontext existierenden `Address` Beans
* zwei oder mehr `Customer` Instanzen anlegt 
* und für diese die `createCustomer()` Methode des injizierten `CustomerService` aufruft
