## Übungen zum Kapitel "120 - RESTful Advanced"

### Error Handling

#### a) Exception DTO

Erstellen Sie eine Klasse `com.example.pizza.error.ExceptionDto`, mittels derer 
Fehlerinformationen (wie z.B. der HTTP Status Code, Fehlertext, Fehlertyp) 
ausgeliefert werden können.

#### b) Optional: Fehlerantwort prüfen

Erstellen Sie einen Test, der für einen fehlerhaften REST-Aufruf prüft, dass eine 
Fehlerantwort auf Basis des Exception-DTO Objekts gesendet wird. Diese Prüfung kann 
z.B. mittels JsonPath erfolgen.

Erst nach Bearbeitung der folgenden zwei Übungen wird dieser Test erfolgreich durchlaufen

#### c) Überarbeitung Exceptions

Überarbeiten Sie die vorhandenen Exception-Klassen der Geschäftslogik, sodass diese 
eine Basisklasse nutzen, in der ein HTTP Status Code hinterlegt werden kann.

#### d) Fehler-Handling implementieren

Implementieren Sie per `@ControllerAdvice` ein generisches Fehler-Handling auf 
Basis der neuen Basisklasse und des Exception-DTOs.

### Model-Konvertierung

#### e) Test-Driven-Development zur verbesserten Kundenanlage

Erstellen Sie einen Test, der prüft, dass trotz Angabe eines "orderCounts" dieser bei 
neu angelegten Kunden immer 0 beträgt.

Wir wollen nicht, dass das Feld "orderCount" weiterhin für neue Kunden angegeben und 
somit vordefiniert werden kann.

Dieser Test wird erst dann erfolgreich sein, wenn die folgenden drei Übungen bearbeitet wurden.

#### f) OrderRequestData

Erstellen Sie eine `OrderRequestData` Klasse, welche genutzt werden soll, um beim 
Anlegen eines neuen Kunden nur bestimmte Attribute zuzulassen. 

#### g) Customer-Converter

Erstellen Sie eine Converter Implementierung, die aus einer `OrderRequestData` 
Instanz eine `Customer` Instanz macht.

#### h) ConversionService nutzen

Nutzen Sie den `ConversionService` im `CustomerRestController`, um ein empfangenes 
`OrderRequestData` in einen `Customer` zu konvertieren und mit diesem dann die 
Geschäftslogik aufzurufen.

### OpenAPI

Fügen Sie die openapi Dependency der POM hinzu.

Ergänzen Sie den OrderRestController und zumindest die ExceptionDto Klasse um Annotationen, die die Dokumentation
lesbarer machen.

### TDD MessageConverter

Schreiben Sie einen Testfall, der ein Upload eines CSV Strings mittels `PUT /products` und MediaType "text/csv"
prüft. Als Ergebnis sollen alle Produkte durch die hochgeladenen Daten ersetzt worden sein.

Schreiben Sie nun die notwendige `AbstractHttpMessageConverter` Klasse.

Registrieren Sie diese in einer neuen `WebMvcConfigurer` Configuration-Bean.

Ergänzen Sie den `ProductRestController` um eine `uploadProducts()` Methode -- sowie den `ProductService` um die
dafür notwendige Geschäftslogik-Methode.