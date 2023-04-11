package pl.lsobotka.green.code.domain.transactions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import org.springframework.lang.NonNull;

public class Account implements Comparable<Account> {

    private final AccountNumber accountNumber;
    private int debitCount;
    private int creditCount;

    private double balance;

    public Account(@NonNull String account) {
        this.accountNumber = new AccountNumber(account);
        this.balance = 0.00;
    }

    public AccountNumber getAccount() {
        return accountNumber;
    }

    public int getDebitCount() {
        return debitCount;
    }

    public int getCreditCount() {
        return creditCount;
    }

    public BigDecimal getBalance() {
        return BigDecimal.valueOf(balance).setScale(2, RoundingMode.HALF_UP);
    }

    public void debit(final double amount) {
        debitCount++;
        balance -= amount;
    }

    public void credit(final double amount) {
        creditCount++;
        balance += amount;
    }

    @Override
    public int compareTo(Account o) {
        return accountNumber.compareTo(o.accountNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return debitCount == account.debitCount && creditCount == account.creditCount && Objects.equals(accountNumber,
                account.accountNumber) && Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, debitCount, creditCount, balance);
    }
}