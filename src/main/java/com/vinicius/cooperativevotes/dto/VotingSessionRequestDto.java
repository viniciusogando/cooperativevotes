package com.vinicius.cooperativevotes.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class VotingSessionRequestDto {

    @NotBlank(message = "agendaId é um campo obrigatório.")
    private String agendaId;

    @NotNull(message = "minutesExpiration é um campo obrigatório.")
    private Integer minutesExpiration;

    public VotingSessionRequestDto(){

    }

    public VotingSessionRequestDto(String agendaId, Integer minutesExpiration) {
        this.agendaId = agendaId;
        this.minutesExpiration = minutesExpiration;
    }

    public String getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(String agendaId) {
        this.agendaId = agendaId;
    }

    public Integer getMinutesExpiration() {
        return minutesExpiration;
    }

    public void setMinutesExpiration(Integer minutesExpiration) {
        this.minutesExpiration = minutesExpiration;
    }
}
