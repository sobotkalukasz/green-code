package pl.lsobotka.green.code.domain.transactions;

import java.util.Objects;

public record AccountNumber(String value) implements Comparable<AccountNumber> {

    public AccountNumber(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public int compareTo(final AccountNumber o) {
        return value.compareTo(o.value());
    }
}
