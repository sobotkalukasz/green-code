package pl.lsobotka.green.code.application.onlinegame;

import java.util.List;

import org.junit.jupiter.api.Test;

import pl.lsobotka.green.code.interfaces.rest.onlinegame.dto.ClanDto;
import pl.lsobotka.green.code.interfaces.rest.onlinegame.dto.PlayersDto;

import static org.assertj.core.api.Assertions.assertThat;

class GroupProcessorTest {

    @Test
    void shouldPrepareGroups_groupByPointsAndPointPerPlayer() {

        // Given
        final ClanDto clanA = new ClanDto(4, 50);
        final ClanDto clanB = new ClanDto(2, 70);
        final ClanDto clanC = new ClanDto(6, 60);
        final ClanDto clanD = new ClanDto(1, 15);
        final ClanDto clanE = new ClanDto(5, 40);
        final ClanDto clanF = new ClanDto(3, 45);
        final ClanDto clanG = new ClanDto(1, 12);
        final ClanDto clanH = new ClanDto(4, 40);

        final PlayersDto playersDto = new PlayersDto(6,
                List.of(clanA, clanB, clanC, clanD, clanE, clanF, clanG, clanH));

        // When
        final List<List<ClanDto>> groups = GroupProcessor.prepareGroups(playersDto);

        // Then
        assertThat(groups.size()).isEqualTo(5);

        final List<ClanDto> groupA = groups.get(0);
        assertThat(groupA.size()).isEqualTo(2);
        assertThat(groupA.get(0)).isEqualTo(clanB);
        assertThat(groupA.get(1)).isEqualTo(clanA);

        final List<ClanDto> groupB = groups.get(1);
        assertThat(groupB.size()).isEqualTo(1);
        assertThat(groupB.get(0)).isEqualTo(clanC);

        final List<ClanDto> groupC = groups.get(2);
        assertThat(groupC.size()).isEqualTo(3);
        assertThat(groupC.get(0)).isEqualTo(clanF);
        assertThat(groupC.get(1)).isEqualTo(clanD);
        assertThat(groupC.get(2)).isEqualTo(clanG);

        final List<ClanDto> groupD = groups.get(3);
        assertThat(groupD.size()).isEqualTo(1);
        assertThat(groupD.get(0)).isEqualTo(clanH);

        final List<ClanDto> groupE = groups.get(4);
        assertThat(groupE.size()).isEqualTo(1);
        assertThat(groupE.get(0)).isEqualTo(clanE);

    }
}