package dk.andreasgabel.talentapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "A document belonging to a talent")
public class Document {

    @Schema(description = "Unique identifier", format = "uuid")
    private final String id;

    @Schema(description = "Document name")
    private final String name;

    @Schema(description = "Document content")
    private final String content;
}
