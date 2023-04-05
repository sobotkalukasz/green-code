package pl.lsobotka.green.code.interfaces.rest.transactions.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TransactionDto(@JsonProperty String debitAccount, @JsonProperty String creditAccount,
                             @JsonProperty BigDecimal amount) {
}
