package com.vinicius.cooperativevotes.model;

import org.springframework.data.annotation.Id;
import javax.validation.constraints.NotEmpty;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "voting_session")
public class VotingSession {

    @Id
    private String id;
    @NotEmpty
    private Agenda agenda;
    @NotEmpty
    private Integer minutesExpiration;

    private LocalDateTime createDate;

    private String status;

    private Integer votesFavor;

    private Integer votesAgainst;

    public VotingSession(Agenda agenda, Integer minutesExpiration) {
        this.agenda = agenda;
        this.minutesExpiration = minutesExpiration;
        this.createDate = LocalDateTime.now();
        this.status = "ABERTA";
        this.votesFavor = 0;
        this.votesAgainst = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Integer getMinutesExpiration() {
        return minutesExpiration;
    }

    public void setMinutesToExpiration(Integer minutesToExpiration) {
        this.minutesExpiration = minutesToExpiration;
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
