package dk.andreasgabel.talentapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "A talent profile")
@JsonPropertyOrder({"id", "name", "title", "profile_text", "email", "phone", "city", "country", "github", "linkedin"})
public class Talent {

    @Schema(description = "Unique identifier", format = "uuid")
    private final String id;

    @Schema(description = "Full name")
    private final String name;

    @Schema(description = "Job title")
    private final String title;

    @Schema(description = "Short introduction to the talent")
    @JsonProperty("profile_text")
    private final String profileText;

    @Schema(description = "Email address", format = "email")
    private final String email;

    @Schema(description = "Phone number")
    private final String phone;

    @Schema(description = "City")
    private final String city;

    @Schema(description = "Country")
    private final String country;

    @Schema(description = "GitHub profile URL", format = "uri", nullable = true)
    private final String github;

    @Schema(description = "LinkedIn profile URL", format = "uri", nullable = true)
    private final String linkedin;
}
