package pl.lsobotka.green.code.domain.atm;

import java.util.Comparator;

public enum AtmRequestType {
    STANDARD(4),
    SIGNAL_LOW(3),
    PRIORITY(2),
    FAILURE_RESTART(1);

    private final int importanceLevel;

    AtmRequestType(int importanceLevel) {
        this.importanceLevel = importanceLevel;
    }

    public int getImportanceLevel() {
        return importanceLevel;
    }

    public int compareByImportanceLevel(final AtmRequestType o) {
        return Comparator.comparingInt(AtmRequestType::getImportanceLevel).compare(this, o);
    }

}
