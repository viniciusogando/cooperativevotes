package com.vinicius.cooperativevotes.dto;

import java.time.LocalDateTime;

public class VotingSessionResponseDto {

    private String id;

    private String agendaId;

    private Integer minutesExpiration;

    private LocalDateTime createDate;

    private String status;

    private Integer votesFavor;

    private Integer votesAgainst;

    public VotingSessionResponseDto(){

    }

    public VotingSessionResponseDto(String id, String agendaId, Integer minutesExpiration, LocalDateTime createDate, String status, Integer votesFavor, Integer votesAgainst) {
        this.id = id;
        this.agendaId = agendaId;
        this.minutesExpiration = minutesExpiration;
        this.createDate = createDate;
        this.status = status;
        this.votesFavor = votesFavor;
        this.votesAgainst = votesAgainst; 
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getVotesFavor() {
        return votesFavor;
    }

    public void setVotesFavor(Integer votesFavor) {
        this.votesFavor = votesFavor;
    }

    public Integer getVotesAgainst() {
        return votesAgainst;
    }

    public void setVotesAgainst(Integer votesAgainst) {
        this.votesAgainst = votesAgainst;
    }
}