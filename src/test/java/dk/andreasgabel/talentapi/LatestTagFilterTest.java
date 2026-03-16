package dk.andreasgabel.talentapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(properties = "IMAGE_TAG=latest")
@AutoConfigureMockMvc
class LatestTagFilterTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void latestTag_redirectsRootToOops() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/oops.html"));
    }

    @Test
    void latestTag_redirectsTalentToOops() throws Exception {
        mvc.perform(get("/talent"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/oops.html"));
    }

    @Test
    void latestTag_allowsOopsPage() throws Exception {
        mvc.perform(get("/oops.html"))
                .andExpect(status().isOk());
    }

    @Test
    void latestTag_allowsImageTagEndpoint() throws Exception {
        mvc.perform(get("/api/image-tag"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tag").value("latest"));
    }

    @Test
    void latestTag_allowsHealthEndpoint() throws Exception {
        mvc.perform(get("/actuator/health"))
                .andExpect(status().isOk());
    }

    @Test
    void latestTag_allowsStaticCss() throws Exception {
        mvc.perform(get("/css/variables.css"))
                .andExpect(status().isOk());
    }
}
