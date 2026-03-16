package dk.andreasgabel.talentapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Locale;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(properties = "IMAGE_TAG=sha-test")
@AutoConfigureMockMvc
class TalentApiIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String andreasId;
    private String nimaId;
    private String firstDocId;

    @BeforeEach
    void setUp() throws Exception {
        MvcResult result = mvc.perform(get("/talent")).andReturn();
        JsonNode talents = objectMapper.readTree(result.getResponse().getContentAsString());

        for (JsonNode t : talents) {
            if ("Andreas Gabel".equals(t.get("name").asText())) {
                andreasId = t.get("id").asText();
            } else if ("Nima Salami".equals(t.get("name").asText())) {
                nimaId = t.get("id").asText();
            }
        }

        MvcResult docsResult = mvc.perform(get("/talent/" + andreasId + "/documents")).andReturn();
        JsonNode docs = objectMapper.readTree(docsResult.getResponse().getContentAsString());
        firstDocId = docs.get(0).get("id").asText();
    }

    // --- GET /talent ---

    @Test
    void getAllTalents_returnsBothTalents() throws Exception {
        mvc.perform(get("/talent"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Andreas Gabel")))
                .andExpect(jsonPath("$[1].name", is("Nima Salami")));
    }

    @Test
    void getAllTalents_containsAllFields() throws Exception {
        mvc.perform(get("/talent"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNotEmpty())
                .andExpect(jsonPath("$[0].email").exists())
                .andExpect(jsonPath("$[0].phone").exists())
                .andExpect(jsonPath("$[0].city").exists())
                .andExpect(jsonPath("$[0].country").exists())
                .andExpect(jsonPath("$[0].github").exists())
                .andExpect(jsonPath("$[0].linkedin").exists())
                .andExpect(jsonPath("$[0].profile_text").isNotEmpty());
    }

    // --- GET /talent/{id} ---

    @Test
    void getTalentById_andreas_returnsTalent() throws Exception {
        mvc.perform(get("/talent/" + andreasId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Andreas Gabel")))
                .andExpect(jsonPath("$.title").isNotEmpty())
                .andExpect(jsonPath("$.profile_text").isNotEmpty());
    }

    @Test
    void getTalentById_nima_returnsTalent() throws Exception {
        mvc.perform(get("/talent/" + nimaId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Nima Salami")))
                .andExpect(jsonPath("$.email", is("nima@nimasalami.dk")));
    }

    @Test
    void getTalentById_invalidId_returns404() throws Exception {
        mvc.perform(get("/talent/does-not-exist"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.message").isNotEmpty());
    }

    // --- GET /talent/{id}/documents ---

    @Test
    void getDocuments_andreas_returnsAllDocuments() throws Exception {
        mvc.perform(get("/talent/" + andreasId + "/documents"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(6)))
                .andExpect(jsonPath("$[0].id").isNotEmpty())
                .andExpect(jsonPath("$[0].name").isNotEmpty())
                .andExpect(jsonPath("$[0].content").isNotEmpty());
    }

    @Test
    void getDocuments_containsMotivationsbrev() throws Exception {
        mvc.perform(get("/talent/" + andreasId + "/documents"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.name == 'Motivationsbrev')]").exists());
    }

    @Test
    void getDocuments_containsCV() throws Exception {
        mvc.perform(get("/talent/" + andreasId + "/documents"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.name == 'CV')]").exists());
    }

    @Test
    void getDocuments_invalidTalent_returns404() throws Exception {
        mvc.perform(get("/talent/does-not-exist/documents"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").isNotEmpty());
    }

    // --- GET /talent/{id}/documents/{documentId} ---

    @Test
    void getDocument_validIds_returnsDocument() throws Exception {
        mvc.perform(get("/talent/" + andreasId + "/documents/" + firstDocId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").isNotEmpty())
                .andExpect(jsonPath("$.content").isNotEmpty());
    }

    @Test
    void getDocument_invalidDocId_returns404() throws Exception {
        mvc.perform(get("/talent/" + andreasId + "/documents/does-not-exist"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").isNotEmpty());
    }

    @Test
    void getDocument_invalidTalentId_returns404() throws Exception {
        mvc.perform(get("/talent/does-not-exist/documents/" + firstDocId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").isNotEmpty());
    }

    // --- /api/image-tag ---

    @Test
    void imageTag_returnsConfiguredTag() throws Exception {
        mvc.perform(get("/api/image-tag"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tag", is("sha-test")));
    }

    // --- /actuator/health ---

    @Test
    void healthEndpoint_returnsUp() throws Exception {
        mvc.perform(get("/actuator/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("UP")));
    }

    // --- Global error handling ---

    @Test
    void unknownEndpoint_returnsJsonError() throws Exception {
        mvc.perform(get("/nonexistent/path")
                .header("Accept-Language", "en"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.error", is("Not Found")))
                .andExpect(jsonPath("$.timestamp").exists());
    }

    // --- UUID format ---

    @Test
    void talentIds_areValidUuids() throws Exception {
        mvc.perform(get("/talent"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", matchesPattern(
                        "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")))
                .andExpect(jsonPath("$[1].id", matchesPattern(
                        "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")));
    }

    @Test
    void documentIds_areValidUuids() throws Exception {
        mvc.perform(get("/talent/" + andreasId + "/documents"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", matchesPattern(
                        "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")));
    }

    // --- i18n error messages ---

    @Test
    void error404_english_returnsEnglishMessage() throws Exception {
        mvc.perform(get("/talent/does-not-exist")
                .header("Accept-Language", "en"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("Talent not found")))
                .andExpect(jsonPath("$.error", is("Not Found")));
    }

    @Test
    void error404_danish_returnsDanishMessage() throws Exception {
        mvc.perform(get("/talent/does-not-exist")
                .header("Accept-Language", "da"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("Talent blev ikke fundet")))
                .andExpect(jsonPath("$.error", is("Ikke fundet")));
    }
}
