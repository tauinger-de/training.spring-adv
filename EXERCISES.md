# Übung zu Kapitel "100 - Profile, Logging und Monitoring"

## a) "Nur Customer” Profil

Markieren Sie alle relevanten Beans mit `@Profile` Annotationen, sodass bei Aufruf 
der Anwendung mit dem Profil "customer" nur die Funktionalität des Customer-Moduls 
zur Verfügung steht.

Gerne können Sie auch die Übung so weit treiben, dass die folgenden Profile zur Auswahl 
stehen und die Anwendung bei jedem der Profile lauffähig ist:
* "product"
* "customer"
* "order"
* kein Profil gesetzt

## b) “Dev” Profil

Legen Sie eine Konfigurationsdatei für das Profil "dev" an, bei dem vermehrt 
Logging Ausgaben generiert werden, z.B. Logging-Level aller "pizza" Klassen auf DEBUG.

## c) System.out durch Logging

Ersetzen Sie Ausgaben in der Anwendung, die bisher per System.out.println() erfolgt sind, 
durch Logging Ausgaben.

## d) Monitoring aktivieren

Aktivieren Sie Monitoring für die Anwendung, sodass folgende Endpunkte im Browser abrufbar sind:
* health
* info
* env

## e) Build Infos

Sie können den Maven Build so erweitern, dass die Datei `META-INF/build-info.properties`
generiert wird.

Diese wird dann von Springs `BuildInfoContributor` ausgelesen und als Teil des "info" Monitoring
Endpoints angezeigt -- sehr praktisch, wenn man wissen will, welcher Commit tatsächlich deployt ist.

Da diese Datei vom Maven Build-Prozess generiert wird, funktioniert es nicht, wenn man direkt von
der IDE aus startet.

## f) Erweiterung Health Monitoring

Aktivieren Sie die Detail-Anzeige des Health-Monitorings:
`management.endpoint.health.show-details=always`

Die Health-Informationen können Sie auf http://localhost:8080/actuator/health abrufen.

Schreiben Sie dann eine Bean, die `HealthIndicator` implementiert. Diese soll das System als
DOWN bewerten, wenn keine bestellbaren Produkte vorhanden sind.

Durch ein zusätzliches Property können Sie dann bspw. den Produkt-Setup deaktivieren und schauen,
dass nun ohne Produkte der System-Gesamtzustand ebenfalls als DOWN bewertet wird.

## g) Metrics

Stellen Sie Prometheus Micrometer Metriken über Einbindung der entsprechenden Dependencies
zur Verfügung.

Rufen Sie die Metriken über die entsprechende URL im Browser oder sonstigem Client ab.

Sie können dann Prometheus via Docker starten und entsprechend konfigurieren, dass Metriken
Ihrer Applikaton von Prometheus gescrapet werden.

Eine Anleitung für alle obigen Themen finden Sie u.a. hier:
https://medium.com/@nikhilsrivastava13/monitoring-in-spring-boot-using-micrometer-and-prometheus-667c6f2a625f

