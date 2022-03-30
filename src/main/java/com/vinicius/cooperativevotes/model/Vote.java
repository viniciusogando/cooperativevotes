package com.vinicius.cooperativevotes.model;

import org.springframework.data.annotation.Id;
import javax.validation.constraints.NotEmpty;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "vote")
public class Vote {
    @Id
    private String id;
    @NotEmpty
    private String cpf;
    @NotEmpty
    private VotingSession votingSession;
    @NotEmpty
    private Boolean vote;

    public Vote(String cpf, VotingSession votingSession, Boolean vote) {
        this.cpf = cpf;
        this.votingSession = votingSession;
        this.vote = vote;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public VotingSession getVotingSession() {
        return votingSession;
    }

    public void setVotingSession(VotingSession votingSession) {
        this.votingSession = votingSession;
    }

    public Boolean getVote() {
        return vote;
    }

    public void setVote(Boolean vote) {
        this.vote = vote;
    }
}
