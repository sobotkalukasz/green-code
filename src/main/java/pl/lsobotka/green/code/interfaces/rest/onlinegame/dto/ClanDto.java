package pl.lsobotka.green.code.interfaces.rest.onlinegame.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ClanDto(@JsonProperty int numberOfPlayers, @JsonProperty int points) {
}
