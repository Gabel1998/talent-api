package dk.andreasgabel.talentapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "En talent-profil med kontaktoplysninger og links til online profiler")
@JsonPropertyOrder({"id", "name", "title", "profile_text", "email", "phone", "city", "country", "github", "linkedin"})
public class Talent {

    @Schema(description = "Unik identifikator (UUID)", example = "550e8400-e29b-41d4-a716-446655440000", format = "uuid")
    private final String id;

    @Schema(description = "Fulde navn", example = "Andreas Gabel")
    private final String name;

    @Schema(description = "Stillingsbetegnelse eller rolle", example = "Datamatiker-studerende | DevOps & Backend")
    private final String title;

    @Schema(description = "Kort introduktion til talentet", example = "4. semester datamatiker med fokus pa DevOps og backend...")
    @JsonProperty("profile_text")
    private final String profileText;

    @Schema(description = "E-mailadresse", example = "andreasgabel98@gmail.com", format = "email")
    private final String email;

    @Schema(description = "Telefonnummer", example = "+45 12 34 56 78")
    private final String phone;

    @Schema(description = "By", example = "Kobenhavn")
    private final String city;

    @Schema(description = "Land", example = "Danmark")
    private final String country;

    @Schema(description = "GitHub-profil URL", example = "https://github.com/Gabel1998", format = "uri", nullable = true)
    private final String github;

    @Schema(description = "LinkedIn-profil URL", example = "https://www.linkedin.com/in/andreas-sogaard-gabel-758991133", format = "uri", nullable = true)
    private final String linkedin;
}
