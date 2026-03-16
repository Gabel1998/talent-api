package dk.andreasgabel.talentapi.controller;

import dk.andreasgabel.talentapi.exception.ResourceNotFoundException;
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
        summary = "Get a list of talents",
        description = "Returns a list of all registered talent profiles with contact info and links."
    )
    @ApiResponse(
        responseCode = "200",
        description = "A list of talents",
        content = @Content(array = @ArraySchema(schema = @Schema(implementation = Talent.class)))
    )
    public ResponseEntity<List<Talent>> getAllTalents() {
        return ResponseEntity.ok(talentService.getAllTalents());
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Get a specific talent",
        description = "Returns a single talent profile by the given ID (UUID)."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "A specific talent",
            content = @Content(schema = @Schema(implementation = Talent.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Talent not found",
            content = @Content
        )
    })
    public ResponseEntity<Talent> getTalentById(
            @Parameter(description = "Talent ID (UUID)", example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable String id) {
        return ResponseEntity.ok(
                talentService.getTalentById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("talent.not_found")));
    }

    @GetMapping("/{id}/documents")
    @Operation(
        summary = "Get documents for a specific talent",
        description = "Returns all documents (CV, cover letter, project descriptions) belonging to the given talent."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "A list of documents",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Document.class)))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Documents not found — talent does not exist",
            content = @Content
        )
    })
    public ResponseEntity<List<Document>> getDocuments(
            @Parameter(description = "Talent ID (UUID)", example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable String id) {
        return ResponseEntity.ok(
                talentService.getDocumentsForTalent(id)
                        .orElseThrow(() -> new ResourceNotFoundException("documents.not_found")));
    }

    @GetMapping("/{id}/documents/{documentId}")
    @Operation(
        summary = "Get a specific document for a talent",
        description = "Returns a single document for a talent, e.g. CV or cover letter."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "A specific document",
            content = @Content(schema = @Schema(implementation = Document.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Document not found",
            content = @Content
        )
    })
    public ResponseEntity<Document> getDocument(
            @Parameter(description = "Talent ID (UUID)", example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable String id,
            @Parameter(description = "Document ID (UUID)", example = "d001")
            @PathVariable String documentId) {
        return ResponseEntity.ok(
                talentService.getDocument(id, documentId)
                        .orElseThrow(() -> new ResourceNotFoundException("document.not_found")));
    }
}
