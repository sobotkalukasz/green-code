package pl.lsobotka.green.code.interfaces.rest.onlinegame;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.lsobotka.green.code.application.onlinegame.OnlineGameService;
import pl.lsobotka.green.code.interfaces.rest.onlinegame.dto.ClanDto;
import pl.lsobotka.green.code.interfaces.rest.onlinegame.dto.PlayersDto;

@RestController
@RequestMapping(path = "/onlinegame")
public class OnlineGameController {

    private final OnlineGameService onlineGameService;

    public OnlineGameController(OnlineGameService onlineGameService) {
        this.onlineGameService = onlineGameService;
    }

    @PostMapping("/calculate")
    @ResponseStatus(HttpStatus.OK)
    public List<List<ClanDto>> calculate(@RequestBody final PlayersDto players) {
        return onlineGameService.calculateGroups(players);
    }
}
