package pl.lsobotka.green.code.application.transactions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.lsobotka.green.code.domain.transactions.Account;
import pl.lsobotka.green.code.interfaces.rest.transactions.dto.TransactionDto;

public class TransactionProcessor {

    private final Map<String, Account> accounts;

    private TransactionProcessor() {
        this.accounts = new HashMap<>();
    }

    public static List<Account> process(final List<TransactionDto> transactions) {
        final TransactionProcessor processor = new TransactionProcessor();
        processor.processTransactions(transactions);
        return processor.getOrderedAccounts();
    }

    private void processTransactions(final List<TransactionDto> transactions) {
        transactions.forEach(this::processTransaction);
    }

    private void processTransaction(final TransactionDto transaction) {
        final Account creditAccount = getAccount(transaction.creditAccount());
        creditAccount.credit(transaction.amount());

        final Account debitAccount = getAccount(transaction.debitAccount());
        debitAccount.debit(transaction.amount());
    }

    private Account getAccount(final String accountNumber) {
        final Account account;
        if (accounts.containsKey(accountNumber)) {
            account = accounts.get(accountNumber);
        } else {
            account = new Account(accountNumber);
            accounts.put(accountNumber, account);
        }
        return account;
    }

    private List<Account> getOrderedAccounts() {
        final List<Account> orderedAccounts = new ArrayList<>(accounts.values());
        Collections.sort(orderedAccounts);
        return orderedAccounts;
    }
}
