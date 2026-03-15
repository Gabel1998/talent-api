package dk.andreasgabel.talentapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Et dokument tilknyttet en talent — f.eks. CV, motivationsbrev eller projektbeskrivelse")
public class Document {

    @Schema(description = "Unik identifikator", example = "d001")
    private final String id;

    @Schema(description = "Dokumentnavn", example = "Motivationsbrev")
    private final String name;

    @Schema(description = "Dokumentets indhold i fuld tekst", example = "Kaere Tech Chapter, jeg soger hermed...")
    private final String content;
}
