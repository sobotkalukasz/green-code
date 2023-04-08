package pl.lsobotka.green.code.domain.atm;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.lsobotka.green.code.domain.atm.AtmRequestType.*;

class AtmRequestTypeTest {

    @Test
    void shouldOrderByImportanceLevel() {
        // Given
        final List<AtmRequestType> requestTypes = Arrays.asList(SIGNAL_LOW, PRIORITY, STANDARD, FAILURE_RESTART);

        // When
        requestTypes.sort(AtmRequestType::compareByImportanceLevel);

        // Then
        assertThat(requestTypes.get(0)).isEqualTo(FAILURE_RESTART);
        assertThat(requestTypes.get(1)).isEqualTo(PRIORITY);
        assertThat(requestTypes.get(2)).isEqualTo(SIGNAL_LOW);
        assertThat(requestTypes.get(3)).isEqualTo(STANDARD);
    }
}