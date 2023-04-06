package pl.lsobotka.green.code.application.transactions;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import pl.lsobotka.green.code.domain.transactions.Account;
import pl.lsobotka.green.code.interfaces.rest.transactions.dto.TransactionDto;

import static org.assertj.core.api.Assertions.assertThat;

class TransactionProcessorTest {

    @Test
    void should_processTransactionsAndReturnOrderedAccounts_when_validTransactions() {

        // Given
        final String accountA = "A";
        final String accountB = "B";
        final String accountC = "C";

        final TransactionDto transactionA = new TransactionDto(accountA, accountC, BigDecimal.valueOf(15.50));
        final TransactionDto transactionB = new TransactionDto(accountA, accountB, BigDecimal.valueOf(10.20));
        final TransactionDto transactionC = new TransactionDto(accountA, accountC, BigDecimal.valueOf(30.50));
        final TransactionDto transactionD = new TransactionDto(accountB, accountC, BigDecimal.valueOf(10.20));

        // When
        final List<Account> actual = TransactionProcessor.process(
                Arrays.asList(transactionA, transactionB, transactionC, transactionD));

        // Then
        assertThat(actual.size()).isEqualTo(3);

        final Account actualA = actual.get(0);
        assertThat(actualA.getAccount().value()).isEqualTo(accountA);
        assertThat(actualA.getCreditCount()).isEqualTo(0);
        assertThat(actualA.getDebitCount()).isEqualTo(3);
        assertThat(actualA.getBalance()).isEqualTo(BigDecimal.valueOf(-15.50 - 10.20 - 30.50));

        final Account actualB = actual.get(1);
        assertThat(actualB.getAccount().value()).isEqualTo(accountB);
        assertThat(actualB.getCreditCount()).isEqualTo(1);
        assertThat(actualB.getDebitCount()).isEqualTo(1);
        assertThat(actualB.getBalance()).isEqualTo(BigDecimal.valueOf(0.00));

        final Account actualC = actual.get(2);
        assertThat(actualC.getAccount().value()).isEqualTo(accountC);
        assertThat(actualC.getCreditCount()).isEqualTo(3);
        assertThat(actualC.getDebitCount()).isEqualTo(0);
        assertThat(actualC.getBalance()).isEqualTo(BigDecimal.valueOf(015.50 + 30.50 + 10.20));
    }

    @Test
    void should_returnEmptyAccountsList_when_noTransactions() {

        // When
        final List<Account> actual = TransactionProcessor.process(Collections.emptyList());

        // Then
        assertThat(actual.size()).isEqualTo(0);

    }

}