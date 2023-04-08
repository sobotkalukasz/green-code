package pl.lsobotka.green.code.domain.atm;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RegionCodeTest {

    @Test
    void shouldSortRegionCodeInAscendingOrder() {
        // Given
        final RegionCode regionCodeA = new RegionCode(1);
        final RegionCode regionCodeB = new RegionCode(5);
        final RegionCode regionCodeC = new RegionCode(8);

        final List<RegionCode> regions = Arrays.asList(regionCodeB, regionCodeA, regionCodeC);

        // When
        Collections.sort(regions);

        // Then
        assertThat(regions.get(0)).isEqualTo(regionCodeA);
        assertThat(regions.get(1)).isEqualTo(regionCodeB);
        assertThat(regions.get(2)).isEqualTo(regionCodeC);
    }

}