package pl.lsobotka.green.code.application.atm;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import pl.lsobotka.green.code.domain.atm.Atm;
import pl.lsobotka.green.code.domain.atm.AtmId;
import pl.lsobotka.green.code.domain.atm.AtmRequestType;
import pl.lsobotka.green.code.domain.atm.RegionCode;
import pl.lsobotka.green.code.domain.atm.Task;

import static org.assertj.core.api.Assertions.assertThat;

class TaskOrderProcessorTest {

    @Test
    void should_useMoreImportantTask_when_multipleTasksForSameAtm() {
        // Given
        final Task taskA = task(1, 2, AtmRequestType.PRIORITY);
        final Task taskB = task(1, 1, AtmRequestType.STANDARD);
        final Task taskC = task(1, 1, AtmRequestType.FAILURE_RESTART);

        final List<Task> tasks = Arrays.asList(taskA, taskB, taskC);

        // When
        final List<Task> orderedTasks = TaskOrderProcessor.orderTasks(tasks);

        // Then
        assertThat(orderedTasks.size()).isEqualTo(2);
        assertThat(orderedTasks.get(0)).isEqualTo(taskC);
        assertThat(orderedTasks.get(1)).isEqualTo(taskA);
        assertThat(orderedTasks.contains(taskB)).isFalse();
    }

    @Test
    void should_orderTaskByRegionAndImportanceLevel_when_multipleTasksForSameAtm() {
        // Given
        final Task taskA = task(1, 1, AtmRequestType.FAILURE_RESTART);
        final Task taskB = task(1, 2, AtmRequestType.PRIORITY);
        final Task taskC = task(1, 3, AtmRequestType.STANDARD);

        final Task taskD = task(2, 5, AtmRequestType.FAILURE_RESTART);
        final Task taskE = task(2, 4, AtmRequestType.PRIORITY);
        final Task taskF = task(2, 3, AtmRequestType.SIGNAL_LOW);
        final Task taskG = task(2, 2, AtmRequestType.STANDARD);
        final Task taskH = task(2, 1, AtmRequestType.STANDARD);

        final Task taskI = task(3, 1, AtmRequestType.STANDARD);

        final Task taskToExclude = task(2, 5, AtmRequestType.STANDARD);

        final List<Task> tasks = Arrays.asList(taskC, taskToExclude, taskG, taskH, taskD, taskI, taskE, taskB, taskF,
                taskA);

        // When
        final List<Task> orderedTasks = TaskOrderProcessor.orderTasks(tasks);

        // Then
        assertThat(orderedTasks.size()).isEqualTo(9);
        assertThat(orderedTasks.get(0)).isEqualTo(taskA);
        assertThat(orderedTasks.get(1)).isEqualTo(taskB);
        assertThat(orderedTasks.get(2)).isEqualTo(taskC);
        assertThat(orderedTasks.get(3)).isEqualTo(taskD);
        assertThat(orderedTasks.get(4)).isEqualTo(taskE);
        assertThat(orderedTasks.get(5)).isEqualTo(taskF);
        assertThat(orderedTasks.get(6)).isEqualTo(taskG);
        assertThat(orderedTasks.get(7)).isEqualTo(taskH);
        assertThat(orderedTasks.get(8)).isEqualTo(taskI);
        assertThat(orderedTasks.contains(taskToExclude)).isFalse();
    }

    private Task task(final int regionCode, final int atmId, final AtmRequestType requestType) {
        final Atm atm = new Atm(new RegionCode(regionCode), new AtmId(atmId));
        return new Task(atm, requestType);
    }

}