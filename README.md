# Talent API

REST API til Tech Chapter praktikant-udfordring. Serverer talent-profiler, dokumenter og projektbeskrivelser via en Spring Boot applikation med Swagger UI og en interaktiv landingsside.

## Endpoints

| Metode | Sti | Beskrivelse |
|--------|-----|-------------|
| `GET` | `/talent` | Alle talent-profiler |
| `GET` | `/talent/{id}` | Specifik talent |
| `GET` | `/talent/{id}/documents` | Dokumenter for en talent |
| `GET` | `/talent/{id}/documents/{docId}` | Specifikt dokument |

Fuld API-dokumentation: [`/swagger-ui.html`](swagger-ui.html)

## Kør lokalt

```bash
mvn spring-boot:run
```

Åbn [localhost:8080](http://localhost:8080) for landingssiden eller [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) for Swagger UI.

## Docker

```bash
# Træk image med specifikt SHA-tag (ikke :latest)
docker pull ghcr.io/gabel1998/talent-api:sha-<commit>

# Kør container
docker run -p 8080:8080 ghcr.io/gabel1998/talent-api:sha-<commit>
```

> **Bemærk:** `:latest` tagget viser en venlig påmindelse om at bruge SHA-baserede tags i stedet.

## Tech stack

- Java 21, Spring Boot 3.4
- Swagger / OpenAPI 3 (springdoc)
- Docker (multi-stage build)
- GitHub Actions CI/CD

## CI/CD Pipeline

| Fase | Beskrivelse |
|------|-------------|
| **CI** | Build, test, JaCoCo, Checkstyle, SpotBugs |
| **CD Delivery** | Push SHA-tagged + latest image til GHCR |
| **CD Deployment** | Opdater SHA i portfolio docker-compose, deploy via SSH |
| **CF** | Health check + smoke test |

## Projektstruktur

```
src/main/
├── java/dk/andreasgabel/talentapi/
│   ├── TalentApiApplication.java
│   ├── config/
│   │   └── LatestTagFilter.java
│   ├── controller/
│   │   ├── TalentController.java
│   │   └── ImageTagController.java
│   ├── model/
│   │   ├── Talent.java
│   │   └── Document.java
│   ├── repository/
│   │   └── TalentRepository.java
│   └── service/
│       └── TalentService.java
└── resources/
    ├── application.properties
    ├── banner.txt
    └── static/
        ├── index.html
        ├── oops.html
        ├── css/          (10 moduler)
        ├── js/           (6 ES modules)
        └── data/i18n/    (da.json, en.json)
```

## Lavet af

**Andreas Gabel** — [andreasgabel.dk](https://andreasgabel.dk) — [GitHub](https://github.com/Gabel1998)

Søger praktik hos Tech Chapter sammen med **Nima Salami** — [nimasalami.dk](https://nimasalami.dk)
