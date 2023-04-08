package pl.lsobotka.green.code.interfaces.rest.onlinegame;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pl.lsobotka.green.code.BaseTest;
import pl.lsobotka.green.code.interfaces.rest.onlinegame.dto.ClanDto;
import pl.lsobotka.green.code.interfaces.rest.onlinegame.dto.PlayersDto;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OnlineGameController.class)
class OnlineGameControllerTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OnlineGameController onlineGameController;

    @Test
    void should_returnGroups_whenPlayersPresent() throws Exception {

        // Given
        final String requestBody = getFileInput("onlinegame/example_request.json");
        final PlayersDto players = objectMapper.readValue(requestBody, PlayersDto.class);

        final String expectedResponseBody = getFileInput("onlinegame/example_response.json");
        final List<List<ClanDto>> groups = objectMapper.readValue(expectedResponseBody, new TypeReference<>() {
        });

        // When
        when(onlineGameController.calculate(players)).thenReturn(groups);

        mockMvc.perform(post("/onlinegame/calculate").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedResponseBody));
    }

}