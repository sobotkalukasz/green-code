package pl.lsobotka.green.code.interfaces.rest.onlinegame.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ClanDtoTest {

    @Test
    void shouldGroupByClanPointsAndPointPerPlayer() {

        // Given
        final ClanDto clanA = new ClanDto(4, 50);
        final ClanDto clanB = new ClanDto(2, 70);
        final ClanDto clanC = new ClanDto(6, 60);
        final ClanDto clanD = new ClanDto(1, 15);
        final ClanDto clanE = new ClanDto(5, 40);
        final ClanDto clanF = new ClanDto(3, 45);
        final ClanDto clanG = new ClanDto(1, 12);
        final ClanDto clanH = new ClanDto(4, 40);

        final List<ClanDto> clans = new ArrayList<>(List.of(clanA, clanB, clanC, clanD, clanE, clanF, clanG, clanH));

        // When
        Collections.sort(clans);

        // Then
        assertThat(clans.size()).isEqualTo(8);

        assertThat(clans.get(0)).isEqualTo(clanB);
        assertThat(clans.get(1)).isEqualTo(clanC);
        assertThat(clans.get(2)).isEqualTo(clanA);
        assertThat(clans.get(3)).isEqualTo(clanF);
        assertThat(clans.get(4)).isEqualTo(clanH);
        assertThat(clans.get(5)).isEqualTo(clanE);
        assertThat(clans.get(6)).isEqualTo(clanD);
        assertThat(clans.get(7)).isEqualTo(clanG);
    }

}