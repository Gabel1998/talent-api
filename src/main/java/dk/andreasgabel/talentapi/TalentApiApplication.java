package dk.andreasgabel.talentapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "Talent API",
        version = "1.0.0",
        description = "REST API til Tech Chapter praktikant-udfordring. "
            + "Returnerer talent-profiler og tilhørende dokumenter (CV, motivationsbrev, projektbeskrivelser). "
            + "Bygget med Spring Boot 3, Java 21, Lombok og Swagger/OpenAPI 3.",
        contact = @Contact(
            name = "Andreas Gabel",
            url = "https://andreasgabel.dk",
            email = "andreasgabel98@gmail.com"
        )
    ),
    servers = {
        @Server(url = "/", description = "Current server")
    }
)
public class TalentApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TalentApiApplication.class, args);
    }
}
