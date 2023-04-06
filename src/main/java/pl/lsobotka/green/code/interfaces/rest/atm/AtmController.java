package pl.lsobotka.green.code.interfaces.rest.atm;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.lsobotka.green.code.application.atm.AtmService;
import pl.lsobotka.green.code.interfaces.rest.atm.dto.AtmDto;
import pl.lsobotka.green.code.interfaces.rest.atm.dto.TaskDto;

@RestController
@RequestMapping(path = "/atms")
public class AtmController {

    private final AtmService atmService;

    public AtmController(AtmService atmService) {
        this.atmService = atmService;
    }

    @PostMapping("/calculateOrder")
    @ResponseStatus(HttpStatus.OK)
    public List<AtmDto> report(@RequestBody final List<TaskDto> tasks) {
        return atmService.calculateAtmOrder(tasks);
    }
}
