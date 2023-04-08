package pl.lsobotka.green.code.application.onlinegame;

import java.util.List;

import org.springframework.stereotype.Service;

import pl.lsobotka.green.code.interfaces.rest.onlinegame.dto.ClanDto;
import pl.lsobotka.green.code.interfaces.rest.onlinegame.dto.PlayersDto;

@Service
public class OnlineGameService {

    public List<List<ClanDto>> calculateGroups(final PlayersDto players) {
        return GroupProcessor.prepareGroups(players);
    }
}
