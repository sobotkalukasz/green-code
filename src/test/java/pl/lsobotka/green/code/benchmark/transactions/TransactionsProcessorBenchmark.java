package pl.lsobotka.green.code.benchmark.transactions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;

import pl.lsobotka.green.code.application.transactions.TransactionProcessor;
import pl.lsobotka.green.code.interfaces.rest.transactions.dto.TransactionDto;

public class TransactionsProcessorBenchmark {

    private static final List<TransactionDto> transactions;

    static {
        final String accountA = "A";
        final String accountB = "B";
        final String accountC = "C";
        transactions = new ArrayList<>();

        for (int i = 0; i < 250_000; i++) {
            transactions.add(new TransactionDto(accountA, accountC, 15.50));
            transactions.add(new TransactionDto(accountA, accountB, 10.20));
            transactions.add(new TransactionDto(accountA, accountC, 30.50));
            transactions.add(new TransactionDto(accountB, accountC, 10.20));
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Warmup(iterations = 1)
    @Fork(1)
    @Measurement(iterations = 3)
    public void benchmark() {
        TransactionProcessor.process(transactions);
    }
}
