package pl.lsobotka.green.code.domain.atm;

import java.util.Objects;
import java.util.StringJoiner;

public class Atm {

    private final RegionCode regionCode;
    private final AtmId atmId;

    public Atm(final RegionCode regionCode, final AtmId atmId) {
        this.regionCode = regionCode;
        this.atmId = atmId;
    }

    public RegionCode getRegionCode() {
        return regionCode;
    }

    public AtmId getAtmId() {
        return atmId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Atm atm = (Atm) o;
        return Objects.equals(regionCode, atm.regionCode) && Objects.equals(atmId, atm.atmId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regionCode, atmId);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Atm.class.getSimpleName() + "[", "]").add("regionCode=" + regionCode)
                .add("atmId=" + atmId)
                .toString();
    }
}
