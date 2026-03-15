package dk.andreasgabel.talentapi.controller;

import dk.andreasgabel.talentapi.model.Document;
import dk.andreasgabel.talentapi.model.Talent;
import dk.andreasgabel.talentapi.service.TalentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/talent")
@Tag(name = "Talent", description = "Endpoints til at hente talent-profiler og deres dokumenter")
public class TalentController {

    private final TalentService talentService;

    public TalentController(TalentService talentService) {
        this.talentService = talentService;
    }

    @GetMapping
    @Operation(
        summary = "Hent alle talents",
        description = "Returnerer en liste over alle registrerede talent-profiler med kontaktinfo og links."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Liste af talents",
        content = @Content(array = @ArraySchema(schema = @Schema(implementation = Talent.class)))
    )
    public ResponseEntity<List<Talent>> getAllTalents() {
        return ResponseEntity.ok(talentService.getAllTalents());
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Hent en specifik talent",
        description = "Returnerer en enkelt talent-profil ud fra det angivne ID (UUID)."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Talent fundet",
            content = @Content(schema = @Schema(implementation = Talent.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Talent ikke fundet — ugyldigt eller ukendt ID",
            content = @Content
        )
    })
    public ResponseEntity<Talent> getTalentById(
            @Parameter(description = "Talent ID (UUID)", example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable String id) {
        return talentService.getTalentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/documents")
    @Operation(
        summary = "Hent dokumenter for en talent",
        description = "Returnerer alle dokumenter (CV, motivationsbrev, projektbeskrivelser) der tilhorer den angivne talent."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Liste af dokumenter",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Document.class)))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Talent ikke fundet",
            content = @Content
        )
    })
    public ResponseEntity<List<Document>> getDocuments(
            @Parameter(description = "Talent ID (UUID)", example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable String id) {
        return talentService.getDocumentsForTalent(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/documents/{documentId}")
    @Operation(
        summary = "Hent et specifikt dokument",
        description = "Returnerer et enkelt dokument for en talent, f.eks. CV eller motivationsbrev."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Dokument fundet",
            content = @Content(schema = @Schema(implementation = Document.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Talent eller dokument ikke fundet",
            content = @Content
        )
    })
    public ResponseEntity<Document> getDocument(
            @Parameter(description = "Talent ID (UUID)", example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable String id,
            @Parameter(description = "Document ID (UUID)", example = "d001")
            @PathVariable String documentId) {
        return talentService.getDocument(id, documentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
