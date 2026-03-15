package dk.andreasgabel.talentapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(properties = "IMAGE_TAG=sha-test")
@AutoConfigureMockMvc
class TalentApiIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getAllTalents_returnsListWithAndreas() throws Exception {
        mvc.perform(get("/talent"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$[0].name", is("Andreas Gabel")))
                .andExpect(jsonPath("$[0].email").exists())
                .andExpect(jsonPath("$[0].github").exists());
    }

    @Test
    void getTalentById_validId_returnsTalent() throws Exception {
        mvc.perform(get("/talent/a1b2c3d4-e5f6-7890-abcd-ef1234567890"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Andreas Gabel")))
                .andExpect(jsonPath("$.title").isNotEmpty())
                .andExpect(jsonPath("$.profile_text").isNotEmpty());
    }

    @Test
    void getTalentById_invalidId_returns404() throws Exception {
        mvc.perform(get("/talent/does-not-exist"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getDocuments_validTalent_returnsDocuments() throws Exception {
        mvc.perform(get("/talent/a1b2c3d4-e5f6-7890-abcd-ef1234567890/documents"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(4))))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].name").isNotEmpty())
                .andExpect(jsonPath("$[0].content").isNotEmpty());
    }

    @Test
    void getDocuments_invalidTalent_returns404() throws Exception {
        mvc.perform(get("/talent/does-not-exist/documents"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getDocument_validIds_returnsDocument() throws Exception {
        mvc.perform(get("/talent/a1b2c3d4-e5f6-7890-abcd-ef1234567890/documents/d0c00001-0000-0000-0000-000000000001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Motivationsbrev")))
                .andExpect(jsonPath("$.content").isNotEmpty());
    }

    @Test
    void getDocument_invalidDocId_returns404() throws Exception {
        mvc.perform(get("/talent/a1b2c3d4-e5f6-7890-abcd-ef1234567890/documents/does-not-exist"))
                .andExpect(status().isNotFound());
    }

    @Test
    void healthEndpoint_returnsUp() throws Exception {
        mvc.perform(get("/actuator/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("UP")));
    }
}
