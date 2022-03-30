package com.vinicius.cooperativevotes.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class VoteDto {
    @NotBlank(message = "VotingSessionId é um campo obrigatório.")
    private String votingSessionId;

    @NotBlank(message = "Cpf é um campo obrigatório.")
    private String cpf;

    @NotNull(message = "Vote é um campo obrigatório.")
    private Boolean vote;

    public VoteDto(){

    }

    public VoteDto(String votingSessionId, String cpf, Boolean vote) {
        this.votingSessionId = votingSessionId;
        this.cpf = cpf;
        this.vote = vote;
    }


    public String getVotingSessionId() {
        return votingSessionId;
    }

    public void setVotingSessionId(String votingSessionId) {
        this.votingSessionId = votingSessionId;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Boolean getVote() {
        return vote;
    }

    public void setVote(Boolean vote) {
        this.vote = vote;
    }
}
