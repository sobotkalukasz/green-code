package pl.lsobotka.green.code.interfaces.rest.onlinegame.dto;

import java.util.Comparator;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ClanDto(@JsonProperty int numberOfPlayers, @JsonProperty int points) implements Comparable<ClanDto> {

    public double pointsPerPlayer() {
        return (double) points / numberOfPlayers;
    }

    @Override
    public int compareTo(@NonNull ClanDto o) {
        return Comparator.comparing(ClanDto::points, Comparator.reverseOrder())
                .thenComparing(ClanDto::pointsPerPlayer, Comparator.reverseOrder())
                .compare(this, o);
    }
}
