## Übungen zum Kapitel "180 - Spring Security"

### a) pom.xml

Fügen Sie die Abhängigkeit für "spring-integration-file" der `pom.xml` hinzu

### b) Neustart - nichts geht mehr

Wenn Sie jetzt die Anwendung neu starten und auf einen Endpunkt zugreifen so werden Sie
überall den Status **401 Unauthorized** erhalten.

Jedoch legt Spring auch in der Default-Konfiguration einen User an, mit dem Sie sich
anmelden können (z.B. mit Basic-Auth). Der Benutzername ist "user" - das Kennwort wird
generiert und im Log ausgegeben.

### c) eigene Security-Config

Erstellen Sie eine eigene Security-Configuration, die alle Requests verbietet - bis auf ein GET auf den
Endpunkt `/orders/greeting`.

Dieser soll für einen Benutzer mit Authority "gast" oder mit Rolle "USER" erlaubt sein.

Benutzer sollen sich mit Basic-Auth anmelden können.

Sie benötigen eine eigene `UserDetailsService` Implementation als Bean. Dieser kann ein Spring `User` Objekt
dynamisch erzeugen.

Ebenfalls brauchen Sie eine `PasswordEncoder` Bean. Hier bietet sich `BCryptPasswordEncoder` von Spring an.

Denken Sie daran, dass das vom `UserDetailsService` ausgelieferte `UserDetails` Objekt ein gehashtes Kennwort
enthalten muss! Für bcrypt kann hier eines generiert werden: https://bcrypt-generator.com/

Testen Sie den Endpunkt mit BasicAuth über z.B. Insomnia.

### d) Oauth2

Ergänzen Sie die Anwendung um die `spring-boot-starter-oauth2-resource-server` Dependency.

