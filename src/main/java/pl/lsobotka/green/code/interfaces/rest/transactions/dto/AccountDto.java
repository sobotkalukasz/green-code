package pl.lsobotka.green.code.interfaces.rest.transactions.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import pl.lsobotka.green.code.domain.transactions.Account;

public record AccountDto(@JsonProperty String account, @JsonProperty int debitCount, @JsonProperty int creditCount,
                         @JsonProperty BigDecimal balance) {

    public static AccountDto from(final Account account) {
        return new AccountDto(account.getAccount().value(), account.getDebitCount(), account.getCreditCount(),
                account.getBalance());
    }
}
