package com.vinicius.cooperativevotes.repository;

import com.vinicius.cooperativevotes.model.Agenda;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AgendaRepository extends MongoRepository<Agenda, String> {

}