package pl.lsobotka.green.code.application.atm;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import pl.lsobotka.green.code.interfaces.rest.atm.dto.AtmDto;
import pl.lsobotka.green.code.interfaces.rest.atm.dto.TaskDto;

@Service
public class AtmService {

    public List<AtmDto> calculateAtmOrder(final List<TaskDto> task){
        return Collections.emptyList(); //TODO implement
    }
}
