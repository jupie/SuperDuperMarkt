# SuperDuperMarkt

# Was ist der SuperDuper Markt
Der SuperDuperMarkt ist eine Beispielapplikation für guten Code. Sie entstand im Rahmen eines Bewerbungsprozesses. 
# Darauf habe ich geachtet
Es war mir wichtig hier moderne Programmierkonzepte umzusetzen. 
## Test first!
Teste zuerst. Jede Funktion hat einen Test erhalten bevor der code geschrieben wurde.  
## classes Should be Small
Klassen sollten nicht mehr als 50 Zeilen enthalten. Dies habe ich bis auf die Mainclass und bei Tests eingehalten.  
## Executing Tests Requires Only One Step/ Project Build Requires Only One Step
Die Ausführung aller Tests soll nur einen Schritt benötigen und der bau des Projekts soll in einem Schritt möglich sein. 
Ich habe mich hier für das Buildtool Maven entschieden, da dieses sehr einfach zu verwenden ist und im gegensatz zu Gradle weniger starke
abhängigkeiten zum Windows System hat. Gradle ist natürlich für größere Projekte und komplexere Aufgabenstellungen die bessere Wahl. 
## Source Control System
Verwende ein Versionskontrollsystem. Dies ist hier geschehen. Nach jedem neuen Feature wurde ein Commit gemacht, so dass ein rollback jederzeit möglich ist. 
## Choose Descriptive / Unambiguous Names
Wähle beschreibende Namen. Dies gilt insbesondere für Klassen und Methodennamen. So wird auch der Agile Ansatz Funktion über Dokumentation erfüllt. 
## Methods Should Do One Thing
Methoden sollten nur eine Sache tun. Dies ist vorallem wichtig, um eine leichte Änderbarkeit des Systems zu gewährleisten. Testen ist dann auch einfacher. 

## Methods Should Descend 1 Level of Abstraction
Methoden sollten maximal einen Level der Abstraktion enthalten. 
## Tests: Fast, Isolated, Repeatable, Self Validating
Tests sollten schnell isoliert, wiederhlbar und Selbst überprüfend sein. Außer die Selbstüberprüfung konnte ich hier alles umsetzen. 

#Allegemeine Prinzipien 
- Keep It Simple, Stupid (KISS) 
- Write DRY Code
- Open/Closed
- Composition Over Inheritance
- Single Responsibility
- Separation of Concerns
- You Aint Gonna Need It (YAGNI)

