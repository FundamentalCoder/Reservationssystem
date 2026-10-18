# Reservationssystem

Webbasierter Terminkalender zur Raumreservation mit **Spring Boot**, **Thymeleaf**, **Spring Data JPA** und **MySQL**.

Das Projekt ermöglicht das Erstellen, Anzeigen, Bearbeiten und Löschen von Raumreservationen. Für jede Reservation werden ein privater und ein öffentlicher Code erzeugt.

## Funktionen

- Reservation für einen Raum erstellen
- Räume **101 bis 105**
- Datum sowie Start- und Endzeit erfassen
- Bemerkung zur Reservation speichern
- Mehrere Teilnehmer erfassen
- Prüfung auf ungültige Datums- und Zeitangaben
- Verhindert überschneidende Reservationen für dasselbe Zimmer
- Automatische Generierung eines **Private Codes**
- Automatische Generierung eines **Public Codes**
- Reservation über den Private Code bearbeiten
- Reservation über den Private Code löschen
- Reservation über den Public Code anzeigen
- Teilnehmer können sich über die öffentliche Ansicht zu einer Reservation hinzufügen
- Speicherung der Reservationen und Teilnehmer in MySQL

## Technologien

- Java 25
- Spring Boot 4.1.1
- Spring MVC
- Spring Data JPA
- Hibernate
- Thymeleaf
- MySQL 8.4
- Maven
- Docker / Docker Compose

## Projekt klonen

```bash
git clone https://github.com/FundamentalCoder/Reservationssystem.git
cd Reservationssystem
```

## Voraussetzungen

Für das lokale Ausführen werden benötigt:

- Java 25
- Docker Desktop bzw. Docker Engine mit Docker Compose

Maven muss nicht separat installiert werden, da das Projekt den Maven Wrapper enthält.

## Datenbank starten

Die MySQL-Datenbank kann über die vorhandene `compose.yaml` gestartet werden:

```bash
docker compose up -d
```

Die Standardkonfiguration verwendet:

| Einstellung | Wert |
|---|---|
| Datenbank | `reservationen` |
| Host | `localhost` |
| Port | `3307` |
| Benutzer | `user` |
| Passwort | `user` |
| MySQL-Version | `8.4` |

Die Datenbankdaten werden in einem Docker-Volume gespeichert.

## Anwendung starten

### Windows

```bash
mvnw.cmd spring-boot:run
```

### Linux / macOS

```bash
./mvnw spring-boot:run
```

Danach ist die Anwendung unter folgender Adresse erreichbar:

```text
http://localhost:8080
```

## Datenbankkonfiguration

Die Verbindung befindet sich in:

```text
src/main/resources/application.properties
```

Aktuelle Konfiguration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3307/reservationen
spring.datasource.username=user
spring.datasource.password=user

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.port=8080
```

Hibernate erstellt bzw. aktualisiert die benötigten Tabellen automatisch.

## Verwendung

### Neue Reservation

Auf der Startseite kann eine neue Reservation erstellt werden.

Dabei werden unter anderem folgende Daten erfasst:

- Datum
- Von-Zeit
- Bis-Zeit
- Zimmer
- Bemerkung
- Teilnehmer

Vor dem Speichern wird geprüft, ob die Eingaben gültig sind und ob für dasselbe Zimmer zur gewählten Zeit bereits eine Reservation existiert.

### Private Code

Nach dem Erstellen wird ein **Private Code** generiert.

Mit diesem Code kann die Reservation:

- angezeigt
- bearbeitet
- gelöscht

werden.

Der Private Code sollte nur an die Person weitergegeben werden, welche die Reservation verwalten darf.

### Public Code

Zusätzlich wird ein **Public Code** generiert.

Mit diesem Code können andere Personen:

- die Reservation ansehen
- die Teilnehmerliste sehen
- sich selbst als Teilnehmer eintragen

Die Reservation kann über den Public Code nicht bearbeitet oder gelöscht werden.

## Zeitüberschneidungen

Für dasselbe Zimmer dürfen sich Reservationen zeitlich nicht überschneiden.

Beispiel:

```text
Bestehende Reservation: 10:00 - 11:00
Neue Reservation:       10:30 - 11:30
```

Diese Reservation wird abgelehnt.

Direkt anschliessende Reservationen sind dagegen möglich:

```text
Reservation 1: 10:00 - 11:00
Reservation 2: 11:00 - 12:00
```

## Projektstruktur

```text
src/main/java/ch/m223/reservationssystem/
├── Controller/
│   ├── StartController.java
│   ├── ReservationController.java
│   ├── PrivateController.java
│   └── PublicController.java
├── Model/
│   ├── Reservation.java
│   └── User.java
└── Repository/
    └── ReservationRepository.java
```

Die Thymeleaf-Seiten befinden sich unter:

```text
src/main/resources/templates/
```

## Wichtige Seiten

| Seite | Beschreibung |
|---|---|
| `index.html` | Startseite und Code-Eingabe |
| `reservation-formular.html` | Neue Reservation erstellen |
| `bestaetigung.html` | Bestätigung und Anzeige der Codes |
| `private-reservation.html` | Private Ansicht |
| `reservation-bearbeiten.html` | Reservation bearbeiten |
| `public-reservation.html` | Öffentliche Ansicht und Teilnahme |

## Anwendung stoppen

Spring Boot kann im Terminal mit `Ctrl + C` beendet werden.

Die Datenbank kann mit folgendem Befehl gestoppt werden:

```bash
docker compose down
```

Um zusätzlich das gespeicherte Docker-Volume zu löschen:

```bash
docker compose down -v
```

**Achtung:** Dadurch werden die gespeicherten Datenbankdaten gelöscht.

## Repository

GitHub:

```text
https://github.com/FundamentalCoder/Reservationssystem
```

## Projektkontext

Dieses Projekt wurde im Rahmen von **Modul 223 – Multiuser-Applikationen objektorientiert realisieren** erstellt.
