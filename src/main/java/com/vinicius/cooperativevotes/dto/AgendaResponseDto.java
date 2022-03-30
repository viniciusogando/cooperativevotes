package com.vinicius.cooperativevotes.dto;

public class AgendaResponseDto {
    private String id;

    private String name;

    private String votingSessionId;

    private String votingSessionStatus;

    private Integer votingSessionVotesFavor;

    private Integer votingSessionVotesAgainst;

    public AgendaResponseDto() {
        
    }

    public AgendaResponseDto(String id, String name, String votingSessionId, String votingSessionStatus, Integer votingSessionVotesFavor, Integer votingSessionVotesAgainst) {
        this.id = id;
        this.name = name;
        this.votingSessionId = votingSessionId;
        this.votingSessionStatus = votingSessionStatus;
        this.votingSessionVotesFavor = votingSessionVotesFavor;
        this.votingSessionVotesAgainst = votingSessionVotesAgainst; 
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVotingSessionId() {
        return votingSessionId;
    }

    public void setVotingSessionId(String votingSessionId) {
        this.votingSessionId = votingSessionId;
    }


    public String getVotingSessionStatus() {
        return votingSessionStatus;
    }

    public void setVotingSessionStatus(String votingSessionStatus) {
        this.votingSessionStatus = votingSessionStatus;
    }

    public Integer getVotingSessionVotesFavor() {
        return votingSessionVotesFavor;
    }

    public void setVotingSessionVotesFavor(Integer votingSessionVotesFavor) {
        this.votingSessionVotesFavor = votingSessionVotesFavor;
    }

    public Integer getVotingSessionVotesAgainst() {
        return votingSessionVotesAgainst;
    }

    public void setVotingSessionVotesAgainst(Integer votingSessionVotesAgainst) {
        this.votingSessionVotesAgainst = votingSessionVotesAgainst;
    }
}
