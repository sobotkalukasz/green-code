package pl.lsobotka.green.code.application.onlinegame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import pl.lsobotka.green.code.interfaces.rest.onlinegame.dto.ClanDto;
import pl.lsobotka.green.code.interfaces.rest.onlinegame.dto.PlayersDto;

public class GroupProcessor {

    public static List<List<ClanDto>> prepareGroups(final PlayersDto players) {
        final List<List<ClanDto>> orderedGroups = new ArrayList<>();
        final int maxPlayersPerGroup = players.groupCount();

        final List<ClanDto> clans = new ArrayList<>(players.clans());
        Collections.sort(clans);

        while (!clans.isEmpty()) {
            final List<ClanDto> group = new ArrayList<>();
            int actualPlayersInGroup = 0;
            final Iterator<ClanDto> iter = clans.iterator();

            while (iter.hasNext() && actualPlayersInGroup < maxPlayersPerGroup) {
                final ClanDto next = iter.next();
                if (actualPlayersInGroup + next.numberOfPlayers() <= maxPlayersPerGroup) {
                    actualPlayersInGroup += next.numberOfPlayers();
                    group.add(next);
                    iter.remove();
                }
            }

            orderedGroups.add(group);
        }
        return orderedGroups;
    }

}
