package pl.lsobotka.green.code.interfaces.rest.atm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import pl.lsobotka.green.code.domain.atm.AtmRequestType;

public record TaskDto(@JsonProperty int region, @JsonProperty AtmRequestType requestType, @JsonProperty int atmId) {
}
