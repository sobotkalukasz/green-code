package pl.lsobotka.green.code.interfaces.rest.transactions;

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
import pl.lsobotka.green.code.application.transactions.TransactionService;
import pl.lsobotka.green.code.interfaces.rest.transactions.dto.AccountDto;
import pl.lsobotka.green.code.interfaces.rest.transactions.dto.TransactionDto;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionsController.class)
class TransactionsControllerTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TransactionService transactionService;

    @Test
    void should_report_when_transactionsExists() throws Exception {
        // Given
        final String requestBody = getFileInput("transactions/example_request.json");
        final List<TransactionDto> transactions = objectMapper.readValue(requestBody, new TypeReference<>() {
        });

        final String expectedResponseBody = getFileInput("transactions/example_response.json");
        final List<AccountDto> accounts = objectMapper.readValue(expectedResponseBody, new TypeReference<>() {
        });

        // When
        when(transactionService.report(transactions)).thenReturn(accounts);

        // Then
        mockMvc.perform(post("/transactions/report").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedResponseBody));

    }

}