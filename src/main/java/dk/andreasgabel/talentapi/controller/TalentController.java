package dk.andreasgabel.talentapi.controller;

import dk.andreasgabel.talentapi.model.Document;
import dk.andreasgabel.talentapi.model.Talent;
import dk.andreasgabel.talentapi.service.TalentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/talent")
@Tag(name = "Talent", description = "Talent API")
public class TalentController {

    private final TalentService talentService;

    public TalentController(TalentService talentService) {
        this.talentService = talentService;
    }

    @GetMapping
    @Operation(summary = "Get a list of talents")
    public ResponseEntity<List<Talent>> getAllTalents() {
        return ResponseEntity.ok(talentService.getAllTalents());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a specific talent")
    public ResponseEntity<Talent> getTalentById(
            @Parameter(description = "Talent ID") @PathVariable String id) {
        return talentService.getTalentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/documents")
    @Operation(summary = "Get documents for a specific talent")
    public ResponseEntity<List<Document>> getDocuments(
            @Parameter(description = "Talent ID") @PathVariable String id) {
        return talentService.getDocumentsForTalent(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/documents/{documentId}")
    @Operation(summary = "Get a specific document for a talent")
    public ResponseEntity<Document> getDocument(
            @Parameter(description = "Talent ID") @PathVariable String id,
            @Parameter(description = "Document ID") @PathVariable String documentId) {
        return talentService.getDocument(id, documentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
