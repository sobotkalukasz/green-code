package pl.lsobotka.green.code.domain.atm;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TaskTest {

    @Test
    void shouldOrderTasksByRegionAndPriority(){
        // Given
        final Task taskA = task(1, 1, AtmRequestType.FAILURE_RESTART);
        final Task taskB = task(1, 2, AtmRequestType.PRIORITY);
        final Task taskC = task(1, 3, AtmRequestType.SIGNAL_LOW);
        final Task taskD = task(1, 3, AtmRequestType.STANDARD);
        final Task taskE = task(2, 1, AtmRequestType.PRIORITY);
        final Task taskF = task(2, 2, AtmRequestType.STANDARD);
        final Task taskG = task(3, 1, AtmRequestType.STANDARD);

        final List<Task> tasks = Arrays.asList(taskF, taskD, taskG, taskB, taskE, taskC, taskA);

        Collections.sort(tasks);

        assertThat(tasks.get(0)).isEqualTo(taskA);
        assertThat(tasks.get(1)).isEqualTo(taskB);
        assertThat(tasks.get(2)).isEqualTo(taskC);
        assertThat(tasks.get(3)).isEqualTo(taskD);
        assertThat(tasks.get(4)).isEqualTo(taskE);
        assertThat(tasks.get(5)).isEqualTo(taskF);
        assertThat(tasks.get(6)).isEqualTo(taskG);
    }

    private Task task(final int regionCode, final int atmId, final AtmRequestType requestType){
        final Atm atm = new Atm(new RegionCode(regionCode), new AtmId(atmId));
        return new Task(atm, requestType);
    }

}