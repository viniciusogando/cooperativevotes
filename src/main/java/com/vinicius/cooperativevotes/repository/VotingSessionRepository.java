package com.vinicius.cooperativevotes.repository;

import java.util.List;

import com.vinicius.cooperativevotes.model.Agenda;
import com.vinicius.cooperativevotes.model.VotingSession;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VotingSessionRepository extends MongoRepository<VotingSession, String> {
    List<VotingSession> findByAgenda(Agenda agenda);
}