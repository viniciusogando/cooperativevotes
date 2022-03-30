package com.vinicius.cooperativevotes.service;

import com.vinicius.cooperativevotes.exception.BadRequestException;
import com.vinicius.cooperativevotes.exception.NotFoundException;
import com.vinicius.cooperativevotes.model.Agenda;
import com.vinicius.cooperativevotes.model.VotingSession;
import com.vinicius.cooperativevotes.repository.AgendaRepository;
import com.vinicius.cooperativevotes.repository.VotingSessionRepository;
import com.vinicius.cooperativevotes.dto.AgendaRequestDto;
import com.vinicius.cooperativevotes.dto.AgendaResponseDto;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AgendaService {

    private final AgendaRepository repository;
    private final VotingSessionRepository votingSessionRepository;
    private final VotingSessionService votingSessionService;


    // Cria uma nova pauta
    public Agenda save(AgendaRequestDto agendaDto) {
        Agenda agenda = new Agenda(agendaDto.getName());
        return repository.save(agenda);
    }

    // Busca uma pauta pelo id
    public AgendaResponseDto get(String id){
        Agenda agenda = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Agenda não encontrada"));

        AgendaResponseDto agendaResponseDto = new AgendaResponseDto();
        agendaResponseDto.setId(id);
        agendaResponseDto.setName(agenda.getName());

        //Verifica se existe uma sessão de votação para esta pauta
        List<VotingSession> votingSessions = votingSessionRepository.findByAgenda(agenda);

        //Se existir retorna id, status, votos a favor e votos contra da sessão de votação
        if (votingSessions.size() > 0) {
            for (VotingSession votingSession: votingSessions) {
                // Valida se a sessão de votação não deve estar FECHADA
                votingSession = votingSessionService.validateVotingSession(votingSession);

                // Preenche os campos de resposta da agenda
                agendaResponseDto.setVotingSessionId(votingSession.getId());
                agendaResponseDto.setVotingSessionStatus(votingSession.getStatus());
                agendaResponseDto.setVotingSessionVotesFavor(votingSession.getVotesFavor());
                agendaResponseDto.setVotingSessionVotesAgainst(votingSession.getVotesAgainst());
            }
        } else {
            // Valores padrões de pautas sem sessão de votação cadastrada
            agendaResponseDto.setVotingSessionId("");
            agendaResponseDto.setVotingSessionStatus("");
            agendaResponseDto.setVotingSessionVotesFavor(0);
            agendaResponseDto.setVotingSessionVotesAgainst(0);
        }

        return agendaResponseDto;
    }
    // Lista todas as pautas
    public List<Agenda> getAll(){
        return repository.findAll();
    }

    // Atualiza o nome de uma pauta pelo id
    public Agenda update(AgendaRequestDto agendaDto, String id){
        Agenda agenda = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Agenda não encontrada"));
        agenda.setName(agendaDto.getName());
        return repository.save(agenda);
    }

    // Deleta uma pauta pelo id
    public void delete(String id) {
        Agenda agenda = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Agenda não encontrada"));

        //Verifica se existe alguma sessão para esta pauta
        List<VotingSession> votingSessions = votingSessionRepository.findByAgenda(agenda);
        
        //Se existir retorna erro informando que não pode excluir
        if (votingSessions.size() > 0) {
            throw new BadRequestException("Não é possível excluir a pauta {" + id + "} pois existe uma sessão de votação vinculada.");
        }

        repository.deleteById(id);
    }
}