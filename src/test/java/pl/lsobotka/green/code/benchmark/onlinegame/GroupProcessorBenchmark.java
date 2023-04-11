package pl.lsobotka.green.code.benchmark.onlinegame;

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

import pl.lsobotka.green.code.application.onlinegame.GroupProcessor;
import pl.lsobotka.green.code.interfaces.rest.onlinegame.dto.ClanDto;
import pl.lsobotka.green.code.interfaces.rest.onlinegame.dto.PlayersDto;

public class GroupProcessorBenchmark {

    private static final PlayersDto players;

    static {
        final ArrayList<ClanDto> clans = new ArrayList<>();
        for (int i = 0; i < 2_000; i++) {
            final ClanDto clanA = new ClanDto(4, 50);
            final ClanDto clanB = new ClanDto(2, 70);
            final ClanDto clanC = new ClanDto(6, 60);
            final ClanDto clanD = new ClanDto(1, 15);
            final ClanDto clanE = new ClanDto(200, 15_000);
            final ClanDto clanF = new ClanDto(3, 45);
            final ClanDto clanG = new ClanDto(1, 12);
            final ClanDto clanH = new ClanDto(4, 40);
            final ClanDto clanI = new ClanDto(4, 40);
            final ClanDto clanJ = new ClanDto(350, 99_999);

            clans.addAll(List.of(clanA, clanB, clanC, clanD, clanE, clanF, clanG, clanH, clanI, clanJ));
        }
        players = new PlayersDto(1000, clans);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Warmup(iterations = 1)
    @Fork(1)
    @Measurement(iterations = 3)
    public void benchmark() {
        GroupProcessor.prepareGroups(players);
    }

}
