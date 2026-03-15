package dk.andreasgabel.talentapi.repository;

import dk.andreasgabel.talentapi.model.Document;
import dk.andreasgabel.talentapi.model.Talent;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class TalentRepository {

    private static final String ANDREAS_ID = "a1b2c3d4-e5f6-7890-abcd-ef1234567890";

    private final List<Talent> talents = List.of(
            Talent.builder()
                    .id(ANDREAS_ID)
                    .name("Andreas Gabel")
                    .title("Datamatiker-studerende | DevOps & Backend")
                    .profileText(
                        "4. semester datamatiker på EK med fokus på DevOps, backend-udvikling og automatisering. " +
                        "Jeg bygger og drifter min egen cloud-native platform på andreasgabel.dk — " +
                        "Spring Boot, Docker, GitHub Actions CI/CD/CD/CF og Nginx på en DigitalOcean droplet. " +
                        "Fire projekter kører i produktion bag én reverse proxy med HTTPS, " +
                        "komplet med JaCoCo, Checkstyle, SpotBugs, Trivy og frontend linting. " +
                        "Har baggrund fra IT-operations hos Københavns Kommune, " +
                        "hvor jeg automatisere drift af Linux-baserede borger-PC'er.")
                    .email("andreassgabel@hotmail.com")
                    .phone(null)
                    .city("København")
                    .country("Denmark")
                    .github("https://github.com/Gabel1998")
                    .linkedin("https://www.linkedin.com/in/andreas-søgaard-gabel-758991133")
                    .build()
    );

    private final Map<String, List<Document>> documents = Map.of(
            ANDREAS_ID, List.of(

                    Document.builder()
                            .id("doc-0001-0000-0000-000000000001")
                            .name("Motivationsbrev")
                            .content(
                                "Jeg søger praktikplads hos Tech Chapter i perioden august til slut oktober 2026. " +
                                "Jeres specialisering i DevOps, SRE og Cloud Native er præcis den retning jeg arbejder hen imod " +
                                "— ikke kun på studiet, men i praksis med egne projekter. " +
                                "Jeg drifter en multi-projekt platform på en DigitalOcean droplet med Docker Compose, " +
                                "Nginx reverse proxy, Let's Encrypt SSL og fuld CI/CD/CD/CF pipeline via GitHub Actions. " +
                                "Fire selvstændige projekter kører i produktion bag samme infrastruktur. " +
                                "På 4. semester er jeg i gang med Kubernetes og Terraform som del af DevOps-faget " +
                                "— det er en direkte forlængelse af det jeg allerede laver i praksis. " +
                                "Det der tiltrækker mig ved Tech Chapter er tilgangen: build-measure-learn, lean startup " +
                                "og et SaaS-produkt der skal bygges ordentligt fra bunden. " +
                                "Jeg kommer med reel driftserfaring og tager ansvar for det arbejde jeg leverer. " +
                                "Min studiekammerat Nima Salami søger også — vi arbejder godt sammen og vil gerne i praktik sammen.")
                            .build(),

                    Document.builder()
                            .id("doc-0002-0000-0000-000000000002")
                            .name("CV")
                            .content(
                                "UDDANNELSE: Datamatiker, Erhvervsakademi København (EK), 2024–2027. " +
                                "TECH STACK — Sprog: Java, JavaScript, SQL, Bash. " +
                                "Frameworks: Spring Boot, Spring Security, Spring JPA, Vanilla JS (ES modules). " +
                                "DevOps & infrastruktur: Docker, Docker Compose, GitHub Actions CI/CD, " +
                                "Nginx, Let's Encrypt SSL, Linux (Ubuntu 24.04), SSH. " +
                                "Code Quality: JaCoCo (coverage), Checkstyle (Google style), SpotBugs (bug detection), " +
                                "HTMLHint, Stylelint, ESLint, Trivy (image scanning). " +
                                "Cloud & hosting: DigitalOcean, GHCR (GitHub Container Registry). " +
                                "Frontend: Vanilla JS, BEM CSS, Three.js, D3.js, Leaflet.js, i18n. " +
                                "Databaser: MySQL, H2, JPA/Hibernate. " +
                                "Andet: Git, PlantUML, Selenium, OAuth2, JWT, Swagger/OpenAPI. " +
                                "Under oplæring (4. semester): Kubernetes, Terraform, avanceret CI/CD. " +
                                "ERHVERVSERFARING: IT-operations, Københavns Kommune (2022–2024): " +
                                "Automatisering af drift af Linux-baserede borger-PC'er, scripting, systemvedligeholdelse. " +
                                "LINK: https://andreasgabel.dk/static/cv-andreas-gabel.pdf")
                            .build(),

                    Document.builder()
                            .id("doc-0003-0000-0000-000000000003")
                            .name("Projekt: Portfolio-platform")
                            .content(
                                "LINK: https://andreasgabel.dk | INFRA: https://andreasgabel.dk/infrastructure.html " +
                                "REPO: github.com/Gabel1998/Portfolio-project (privat). " +
                                "BESKRIVELSE: Containeriseret multi-projekt platform på en DigitalOcean droplet. " +
                                "Spring Boot backend + Vanilla JS frontend. Nginx reverse proxy med Let's Encrypt SSL. " +
                                "Fire projekter kører i produktion bag samme infrastruktur. " +
                                "Auto-genereret infrastruktur-side med PlantUML deployment diagram, " +
                                "live CI/CD pipeline-visualisering via GitHub API og container-detaljer. " +
                                "Fuld CI/CD/CD/CF pipeline: build, test, JaCoCo coverage, Checkstyle, SpotBugs, " +
                                "frontend linting (HTMLHint, Stylelint, ESLint), Docker image push til GHCR, " +
                                "Trivy sårbarhedsscanning, SSH deploy og health check. " +
                                "STACK: Java, Spring Boot, Docker, Nginx, GitHub Actions, GHCR, DigitalOcean, " +
                                "Vanilla JS, Three.js, PlantUML, Let's Encrypt.")
                            .build(),

                    Document.builder()
                            .id("doc-0004-0000-0000-000000000004")
                            .name("Projekt: Raid Fines")
                            .content(
                                "LINK: https://andreasgabel.dk/raidfines " +
                                "REPO: https://github.com/Gabel1998/raid-fines. " +
                                "BESKRIVELSE: WoW raid-bødesystem med OAuth2 login (Discord/GitHub). " +
                                "Spring Boot backend, MySQL database, Vanilla JS frontend. " +
                                "Fuld CI/CD pipeline med build, test, linting, GHCR push, Trivy scanning og SSH deploy. " +
                                "Showroom-version kører med H2 in-memory database. " +
                                "STACK: Java, Spring Boot, Spring Security, OAuth2, JWT, MySQL, Docker, GitHub Actions.")
                            .build(),

                    Document.builder()
                            .id("doc-0005-0000-0000-000000000005")
                            .name("Projekt: Web Crawler")
                            .content(
                                "LINK: https://andreasgabel.dk/webcrawler " +
                                "REPO: https://github.com/Gabel1998/webCrawler. " +
                                "BESKRIVELSE: Konfigurerbar web crawler med visuel output. " +
                                "Crawler et måldomæne, kortlægger URL-relationer og renderer en interaktiv " +
                                "D3.js netværksvisualisering. Selenium til JavaScript-tunge sites. " +
                                "Groq API integration til AI-baseret indholdsanalyse. " +
                                "STACK: Java, Spring Boot, Selenium, D3.js, Groq API, Docker, GitHub Actions.")
                            .build(),

                    Document.builder()
                            .id("doc-0006-0000-0000-000000000006")
                            .name("Projekt: Beskyttelsesrum Danmark")
                            .content(
                                "LINK: https://andreasgabel.dk/beskyttelsesrum " +
                                "REPO: https://github.com/Gabel1998/beskyttelsesrum. " +
                                "BESKRIVELSE: Interaktivt overblik over danske beskyttelsesrum. " +
                                "Gennemse kommuner, se kapacitetsrapporter, udforsk et interaktivt Leaflet.js kort " +
                                "og administrer vedligeholdelsesplaner. Full-stack Spring Boot applikation. " +
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
