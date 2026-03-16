package dk.andreasgabel.talentapi.repository;

import dk.andreasgabel.talentapi.model.Document;
import dk.andreasgabel.talentapi.model.Talent;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TalentRepository {

    private static final String ANDREAS_ID = UUID.randomUUID().toString();
    private static final String NIMA_ID = UUID.randomUUID().toString();

    private final List<Talent> talents = List.of(
            Talent.builder()
                    .id(ANDREAS_ID)
                    .name("Andreas Gabel")
                    .title("Datamatiker-studerende | DevOps & Backend")
                    .profileText(
                        "Min vej hertil er lidt anderledes.\n" +
                        "Jeg har navigeret skibe i søværnet, studeret teologi, latin og filosofi på KU " +
                        "og undervist elever med autisme.\n" +
                        "Den erfaring har givet mig noget de fleste IT-studerende ikke har: " +
                        "at fungere under ansvar i situationer der ikke følger en opskrift.\n\n" +
                        "I søværnet lærte jeg at én mand er ingen mand " +
                        "— hos Tech Chapter kalder de det at løfte i flok.\n" +
                        "Det er den samme overbevisning jeg tager med ind i DevOps.")
                    .email("andreassgabel@hotmail.com")
                    .phone("+45 60 77 66 13")
                    .city("København")
                    .country("Danmark")
                    .github("https://github.com/Gabel1998")
                    .linkedin("https://www.linkedin.com/in/andreas-søgaard-gabel-758991133")
                    .build(),
            Talent.builder()
                    .id(NIMA_ID)
                    .name("Nima Salami")
                    .title("Fra drift til DevOps | Datamatikerstuderende på 4. semester")
                    .profileText(
                        "Fra køkken til kommunal drift til infrastruktur.\n" +
                        "Fælles for det hele er at jeg altid har forstået hvordan arbejde faktisk udføres, " +
                        "hvor friktionen er og hvordan den opstår.\n" +
                        "Det er den sans der naturligt har ført mig mod DevOps.\n\n" +
                        "Jeg drifter egne produktionskørende servere i fritiden " +
                        "og drives af en nysgerrighed der ikke stopper ved at få noget til at virke.\n" +
                        "Jeg vil forstå hvorfor det virker, og hvad der sker når det ikke gør.")
                    .email("nima@nimasalami.dk")
                    .phone("+45 22 98 50 77")
                    .city("København NV")
                    .country("Danmark")
                    .github("https://github.com/hajisan")
                    .linkedin("https://www.linkedin.com/in/hello-nima/")
                    .build()
    );

    private final Map<String, List<Document>> documents = Map.of(
            ANDREAS_ID, List.of(

                    Document.builder()
                            .id(UUID.randomUUID().toString())
                            .name("Motivationsbrev")
                            .content(
                                "Jeg vil bygge rigtige systemer, ikke kun beskrivelser.\n\n" +
                                "Jeg tillader mig herved at søge praktikplads hos Tech Chapter " +
                                "i perioden slut august til start november 2026.\n" +
                                "Jeres specialisering i DevOps, SRE og Cloud Native er præcis den retning jeg arbejder hen imod.\n" +
                                "En af grundene til jeg søger praktik hos jer er at drage læring på teknologier som Kubernetes.\n\n" +
                                "Jeg møder op med en forståelse for hvad der sker når ting kører i virkeligheden, " +
                                "og håber at opnå endnu mere læring hos jer, så jeg kan løfte mig selv og mine systemer " +
                                "op på et højere niveau, og kontinuerligt lærer gennem andre og gennem livet.\n" +
                                "I løser virkelige problemer for virksomheder der ikke har råd til nedetid " +
                                "— det er præcis den skala jeg vil lære at arbejde i.\n\n" +
                                "Det der tiltrækker mig ved Tech Chapter er tilgangen: build-measure-learn, lean startup " +
                                "og et SaaS-produkt der skal bygges ordentligt fra bunden.\n" +
                                "Jeg kommer med reel driftserfaring og tager ansvar for det arbejde jeg leverer " +
                                "— både som individ, og som team.")
                            .build(),

                    Document.builder()
                            .id(UUID.randomUUID().toString())
                            .name("CV")
                            .content(
                                "UDDANNELSE:\nDatamatiker, Erhvervsakademi København (EK), 2024–2027.\n\n" +
                                "TECH STACK:\n" +
                                "Sprog: Java, JavaScript, SQL, Bash.\n" +
                                "Frameworks: Spring Boot, Spring Security, Spring JPA, Vanilla JS (ES modules).\n" +
                                "DevOps & infrastruktur: Docker, Docker Compose, GitHub Actions CI/CD, " +
                                "Nginx, Let's Encrypt SSL, Linux (Ubuntu 24.04), SSH.\n" +
                                "Code Quality: JaCoCo (coverage), Checkstyle (Google style), SpotBugs (bug detection), " +
                                "HTMLHint, Stylelint, ESLint, Trivy (image scanning).\n" +
                                "Cloud & hosting: DigitalOcean, GHCR (GitHub Container Registry).\n" +
                                "Frontend: Vanilla JS, BEM CSS, Three.js, D3.js, Leaflet.js, i18n.\n" +
                                "Databaser: MySQL, H2, JPA/Hibernate.\n" +
                                "Andet: Git, PlantUML, Selenium, OAuth2, JWT, Swagger/OpenAPI.\n" +
                                "Under oplæring (4. semester): Kubernetes, Terraform, avanceret CI/CD.\n\n" +
                                "ERHVERVSERFARING:\n" +
                                "IT-operations, Københavns Kommune (2022–2024):\n" +
                                "Automatisering af drift af Linux-baserede borger-PC'er, scripting, systemvedligeholdelse.\n\n" +
                                "LINK: https://andreasgabel.dk/static/cv-andreas-gabel.pdf")
                            .build(),

                    Document.builder()
                            .id(UUID.randomUUID().toString())
                            .name("Projekt: Portfolio-platform")
                            .content(
                                "LINK: https://andreasgabel.dk\n" +
                                "INFRA: https://andreasgabel.dk/infrastructure.html\n" +
                                "REPO: github.com/Gabel1998/Portfolio-project (privat).\n\n" +
                                "BESKRIVELSE:\n" +
                                "Containeriseret multi-projekt platform på en DigitalOcean droplet.\n" +
                                "Spring Boot backend + Vanilla JS frontend. Nginx reverse proxy med Let's Encrypt SSL.\n" +
                                "Fire projekter kører i produktion bag samme infrastruktur.\n" +
                                "Auto-genereret infrastruktur-side med PlantUML deployment diagram, " +
                                "live CI/CD pipeline-visualisering via GitHub API og container-detaljer.\n\n" +
                                "Fuld CI/CD/CD/CF pipeline: build, test, JaCoCo coverage, Checkstyle, SpotBugs, " +
                                "frontend linting (HTMLHint, Stylelint, ESLint), Docker image push til GHCR, " +
                                "Trivy sårbarhedsscanning, SSH deploy og health check.\n\n" +
                                "STACK: Java, Spring Boot, Docker, Nginx, GitHub Actions, GHCR, DigitalOcean, " +
                                "Vanilla JS, Three.js, PlantUML, Let's Encrypt.")
                            .build(),

                    Document.builder()
                            .id(UUID.randomUUID().toString())
                            .name("Projekt: Raid Fines")
                            .content(
                                "LINK: https://andreasgabel.dk/raidfines\n" +
                                "REPO: https://github.com/Gabel1998/raid-fines.\n\n" +
                                "BESKRIVELSE:\n" +
                                "WoW raid-bødesystem med OAuth2 login via Battle.net og GitHub.\n" +
                                "Spring Boot backend, MySQL database, Vanilla JS frontend.\n" +
                                "Inkluderer en custom in-game addon skrevet i Lua der automatisk tracker bøder under raids.\n" +
                                "Fuld CI/CD pipeline med build, test, linting, GHCR push, Trivy scanning og SSH deploy.\n" +
                                "Showroom-version kører med H2 in-memory database.\n\n" +
                                "STACK: Java, Spring Boot, Spring Security, OAuth2, JWT, MySQL, Lua, Docker, GitHub Actions.")
                            .build(),

                    Document.builder()
                            .id(UUID.randomUUID().toString())
                            .name("Projekt: Web Crawler")
                            .content(
                                "LINK: https://andreasgabel.dk/webcrawler\n" +
                                "REPO: https://github.com/Gabel1998/webCrawler.\n\n" +
                                "BESKRIVELSE:\n" +
                                "Konfigurerbar web crawler med visuel output.\n" +
                                "Crawler et måldomæne, kortlægger URL-relationer og renderer en interaktiv " +
                                "D3.js netværksvisualisering.\n" +
                                "Selenium til JavaScript-tunge sites.\n" +
                                "Groq API integration til AI-baseret indholdsanalyse.\n\n" +
                                "STACK: Java, Spring Boot, Selenium, D3.js, Groq API, Docker, GitHub Actions.")
                            .build(),

                    Document.builder()
                            .id(UUID.randomUUID().toString())
                            .name("Projekt: Beskyttelsesrum Danmark")
                            .content(
                                "LINK: https://andreasgabel.dk/beskyttelsesrum\n" +
                                "REPO: https://github.com/Gabel1998/beskyttelsesrum.\n\n" +
                                "BESKRIVELSE:\n" +
                                "Interaktivt overblik over danske beskyttelsesrum.\n" +
                                "Gennemse kommuner, se kapacitetsrapporter, udforsk et interaktivt Leaflet.js kort " +
                                "og administrer vedligeholdelsesplaner.\n" +
                                "Full-stack Spring Boot applikation.\n\n" +
                                "STACK: Java, Spring Boot, JPA/Hibernate, Leaflet.js, H2, Docker, GitHub Actions.")
                            .build()
            )
    );

    public List<Talent> findAll() {
        return talents;
    }

    public Optional<Talent> findById(String id) {
        return talents.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    public List<Document> findDocumentsByTalentId(String talentId) {
        return documents.getOrDefault(talentId, List.of());
    }

    public Optional<Document> findDocumentById(String talentId, String documentId) {
        return findDocumentsByTalentId(talentId).stream()
                .filter(d -> d.getId().equals(documentId))
                .findFirst();
    }
}
