package pl.lsobotka.green.code.domain.atm;

import java.util.Comparator;

import org.springframework.lang.NonNull;

public record RegionCode(int code) implements Comparable<RegionCode> {

    @Override
    public int compareTo(@NonNull final RegionCode o) {
        return Comparator.comparingInt(RegionCode::code).compare(this, o);
    }
}
