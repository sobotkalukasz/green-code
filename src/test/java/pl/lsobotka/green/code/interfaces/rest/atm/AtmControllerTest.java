package pl.lsobotka.green.code.interfaces.rest.atm;

import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pl.lsobotka.green.code.BaseTest;
import pl.lsobotka.green.code.application.atm.AtmService;
import pl.lsobotka.green.code.interfaces.rest.atm.dto.AtmDto;
import pl.lsobotka.green.code.interfaces.rest.atm.dto.TaskDto;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AtmController.class)
class AtmControllerTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AtmService atmService;

    @ParameterizedTest
    @ValueSource(ints = { 1, 2 })
    void should_orderAtms_when_tasksExists(int exampleNumber) throws Exception {
        // Given
        final String requestBody = getFileInput("atm/example_" + exampleNumber + "_request.json");
        final List<TaskDto> tasks = objectMapper.readValue(requestBody, new TypeReference<>() {
        });

        final String expectedResponseBody = getFileInput("atm/example_" + exampleNumber + "_response.json");
        final List<AtmDto> atms = objectMapper.readValue(expectedResponseBody, new TypeReference<>() {
        });

        // When
        when(atmService.calculateAtmOrder(tasks)).thenReturn(atms);

        // Then
        mockMvc.perform(post("/atms/calculateOrder").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedResponseBody));

    }

}