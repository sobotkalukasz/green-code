package pl.lsobotka.green.code.application.atm;

import java.util.List;

import org.springframework.stereotype.Service;

import pl.lsobotka.green.code.domain.atm.Task;
import pl.lsobotka.green.code.interfaces.rest.atm.dto.AtmDto;
import pl.lsobotka.green.code.interfaces.rest.atm.dto.TaskDto;

@Service
public class AtmService {

    public List<AtmDto> calculateAtmOrder(final List<TaskDto> tasks) {
        final List<Task> taskList = tasks.stream().map(Task::from).toList();
        return TaskOrderProcessor.orderTasks(taskList).stream().map(task -> AtmDto.from(task.getAtm())).toList();
    }
}
