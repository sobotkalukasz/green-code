package pl.lsobotka.green.code.interfaces.rest.onlinegame.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PlayersDto(@JsonProperty int groupCount, @JsonProperty List<ClanDto> clans) {
}
