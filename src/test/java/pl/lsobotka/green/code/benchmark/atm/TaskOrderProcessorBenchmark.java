package pl.lsobotka.green.code.benchmark.atm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;

import pl.lsobotka.green.code.application.atm.TaskOrderProcessor;
import pl.lsobotka.green.code.domain.atm.Atm;
import pl.lsobotka.green.code.domain.atm.AtmId;
import pl.lsobotka.green.code.domain.atm.AtmRequestType;
import pl.lsobotka.green.code.domain.atm.RegionCode;
import pl.lsobotka.green.code.domain.atm.Task;

public class TaskOrderProcessorBenchmark {

    private static final List<Task> tasks;

    static {
        tasks = new ArrayList<>();
        for (int i = 3_000; i >= 0; i = i - 3) {
            final Task taskA = task(i, 1, AtmRequestType.FAILURE_RESTART);
            final Task taskB = task(i, 2, AtmRequestType.PRIORITY);
            final Task taskC = task(i, 3, AtmRequestType.STANDARD);

            final Task taskD = task(i - 1, 5, AtmRequestType.FAILURE_RESTART);
            final Task taskE = task(i - 1, 4, AtmRequestType.PRIORITY);
            final Task taskF = task(i - 1, 3, AtmRequestType.SIGNAL_LOW);
            final Task taskG = task(i - 1, 2, AtmRequestType.STANDARD);
            final Task taskH = task(i - 1, 1, AtmRequestType.STANDARD);

            final Task taskI = task(i - 2, 1, AtmRequestType.STANDARD);

            final Task taskToExclude = task(i - 1, 5, AtmRequestType.STANDARD);
            tasks.addAll(Arrays.asList(taskC, taskToExclude, taskG, taskH, taskD, taskI, taskE, taskB, taskF, taskA));
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Warmup(iterations = 1)
    @Fork(1)
    @Measurement(iterations = 3)
    public void benchmark() {
        TaskOrderProcessor.orderTasks(tasks);
    }

    private static Task task(final int regionCode, final int atmId, final AtmRequestType requestType) {
        final Atm atm = new Atm(new RegionCode(regionCode), new AtmId(atmId));
        return new Task(atm, requestType);
    }

}
