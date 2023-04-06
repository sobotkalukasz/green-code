package pl.lsobotka.green.code.interfaces.rest.atm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import pl.lsobotka.green.code.domain.atm.AtmRequestType;

public record AtmDto(@JsonProperty int region, @JsonProperty int atmId) {
}
