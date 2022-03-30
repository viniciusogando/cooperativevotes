package com.vinicius.cooperativevotes.repository;

import java.util.List;

import com.vinicius.cooperativevotes.model.Vote;
import com.vinicius.cooperativevotes.model.VotingSession;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface VoteRepository extends MongoRepository<Vote, String> {
    List<Vote> findByCpf(String cpf);

    List<Vote> findByVotingSession(VotingSession votingSession);

    List<Vote> findByCpfAndVotingSession(String cpf, VotingSession votingSession);

    Long countByVotingSessionAndVote(VotingSession votingSession, Boolean vote);
}