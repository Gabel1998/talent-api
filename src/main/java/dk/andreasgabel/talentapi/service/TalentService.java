package dk.andreasgabel.talentapi.service;

import dk.andreasgabel.talentapi.model.Document;
import dk.andreasgabel.talentapi.model.Talent;
import dk.andreasgabel.talentapi.repository.TalentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TalentService {

    private final TalentRepository repository;

    public TalentService(TalentRepository repository) {
        this.repository = repository;
    }

    public List<Talent> getAllTalents() {
        return repository.findAll();
    }

    public Optional<Talent> getTalentById(String id) {
        return repository.findById(id);
    }

    public Optional<List<Document>> getDocumentsForTalent(String talentId) {
        return repository.findById(talentId)
                .map(talent -> repository.findDocumentsByTalentId(talent.getId()));
    }

    public Optional<Document> getDocument(String talentId, String documentId) {
        return repository.findById(talentId)
                .flatMap(talent -> repository.findDocumentById(talentId, documentId));
    }
}
