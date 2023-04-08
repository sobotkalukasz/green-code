package pl.lsobotka.green.code.interfaces.rest.atm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import pl.lsobotka.green.code.domain.atm.Atm;

public record AtmDto(@JsonProperty int region, @JsonProperty int atmId) {

    public static AtmDto from(final Atm atm) {
        return new AtmDto(atm.getRegionCode().code(), atm.getAtmId().id());
    }
}
