package nl.hu.cisq1.lingo.trainer.presentation;

import nl.hu.cisq1.lingo.CiTestConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasLength;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@ActiveProfiles("ci")
@Import(CiTestConfiguration.class)
@AutoConfigureMockMvc
class TrainerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("doesnt start game when word not is given")
    void startGameWithoutWord() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/game/start");

        mockMvc.perform(request)
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("only starts game when word is given")
    void startGameWithWord() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/game/start")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"word\":\"makker\"}");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentHint.hintString").value("m....."))
                .andExpect(jsonPath("$.lost").value(false));
    }

    @Test
    @DisplayName("only starts game when word is given")
    void getExistingGame() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/game/1");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.currentHint.hintString").value("p...."));
    }

    @Test
    @DisplayName("guess word")
    void guessWord() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/game/2/guess")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"guess\":\"osmane\"}");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.currentHint.hintString").value("o....e"))
                .andExpect(jsonPath("$.lastFeedback.attempt").value("osmane"));
    }

}
