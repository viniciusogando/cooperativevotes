package com.vinicius.cooperativevotes.service;

import com.vinicius.cooperativevotes.exception.BadRequestException;
import com.vinicius.cooperativevotes.exception.NotFoundException;
import com.vinicius.cooperativevotes.model.VotingSession;
import com.vinicius.cooperativevotes.model.Agenda;
import com.vinicius.cooperativevotes.repository.VotingSessionRepository;
import com.vinicius.cooperativevotes.repository.AgendaRepository;
import com.vinicius.cooperativevotes.repository.VoteRepository;
import com.vinicius.cooperativevotes.dto.VotingSessionRequestDto;
import com.vinicius.cooperativevotes.dto.VotingSessionResponseDto;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class VotingSessionService {

    private final VotingSessionRepository repository;
    private final AgendaRepository agendaRepository;
    private final VoteRepository voteRepository;

    // Cria uma sessão de votação
    public VotingSessionResponseDto save(VotingSessionRequestDto votingSessionDto) {
        String agendaId = votingSessionDto.getAgendaId();
        
        // Verifica se existe a pauta para sessão de votação, se não retorna um erro
        Agenda agenda = this.agendaRepository.findById(agendaId).
                orElseThrow(() -> new NotFoundException("Pauta de votação {" + agendaId + "} não encontrada"));
        VotingSession votingSession = new VotingSession(agenda, votingSessionDto.getMinutesExpiration());

        // Verifica se não existe nenhuma sessão de votação para a pauta informada
        if (repository.findByAgenda(agenda).size() > 0) {
            //Se existir retorna um erro
            throw new BadRequestException("Já existe uma sessão de votação cadastrada para esta pauta {" + agendaId + "}");
        }

        votingSession = repository.save(votingSession);
        return this.votingSessionResponseDto(votingSession);
    }

     // Busca uma sessão de votação pelo id
     public VotingSessionResponseDto get(String id){
        // Retorna erro caso a sessão de votação não exista
        VotingSession votingSession = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Votação de sessão {" + id +"} não encontrada"));
        
        return this.votingSessionResponseDto(votingSession);
    }

    // Lista todas sessões de votação
    public List<VotingSessionResponseDto> getAll(){
        List<VotingSession> votingSessions = repository.findAll();

        List<VotingSessionResponseDto> votingSessionResponseDtos = new ArrayList<VotingSessionResponseDto>();

        // Cria as VotingSessionResponseDto para o retorno da api
        for (VotingSession votingSession: votingSessions) {
            votingSessionResponseDtos.add(this.votingSessionResponseDto(votingSession));
        }

        return votingSessionResponseDtos;
    }

    // Atualiza o status de uma sessão de votação para FECHADA
    public void close(String id) {
        // Retorna erro caso a sessão de votação não exista
        VotingSession votingSession = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Votação de sessão {" + id +"} não encontrada"));
        
        // Contabliza os votos da sessão de votação e atualiza o status para FECHADA
        this.closeVotingSession(votingSession);
    }


    // Recebe uma VotingSession e retorna uma VotingSessionResponseDto
    public VotingSessionResponseDto votingSessionResponseDto(VotingSession votingSession) {
        VotingSessionResponseDto votingSessionResponseDto = new VotingSessionResponseDto();
        votingSessionResponseDto.setId(votingSession.getId().toString());
        votingSessionResponseDto.setAgendaId(votingSession.getAgenda().getId().toString());
        votingSessionResponseDto.setMinutesExpiration(votingSession.getMinutesExpiration());
        votingSessionResponseDto.setCreateDate(votingSession.getCreateDate());
       
        // Verifica se a sessão não deveria estar fechada
        votingSession = this.validateVotingSession(votingSession);

        votingSessionResponseDto.setStatus(votingSession.getStatus());
        votingSessionResponseDto.setVotesFavor(votingSession.getVotesFavor());
        votingSessionResponseDto.setVotesAgainst(votingSession.getVotesAgainst());

        return votingSessionResponseDto;
    }

    // Valida se uma sessão de votação deve fechar
    public VotingSession validateVotingSession(VotingSession votingSession) {
        //Se a sessão de votação estiver fechada retorna ela mesma sem necessidade de fazer a validação
        if (Objects.equals(votingSession.getStatus(), "FECHADA")) {
            return votingSession;
        }

        LocalDateTime createDate = votingSession.getCreateDate();
        // Data de expiração, baseado na data de criação somando os minutos para expiração
        LocalDateTime expirationDate = createDate.plusMinutes(votingSession.getMinutesExpiration());

        // Verifica se a data de expiração é antes da data atual, isso quer dizer que já expirou
        if (expirationDate.isBefore(LocalDateTime.now())) {
            // Se já expirou contabiliza os votos da sessão de votação e atualiza o status para FECHADA
            votingSession = this.closeVotingSession(votingSession);
        }

        return votingSession;
    }

    // Fecha uma sessão de votação e contabiliza os votos
    public VotingSession closeVotingSession(VotingSession votingSession) {
        // Busca todos os votos dessa sessão de votação com o campo Vote true
        Integer votesFavor = voteRepository.countByVotingSessionAndVote(votingSession, true).intValue();
        // Busca todos os votos dessa sessão de votação com o campo Vote false
        Integer votesAgainst = voteRepository.countByVotingSessionAndVote(votingSession, false).intValue();

        // Atualiza o campos de votos a favor e votos contra e o status para FECHADA
        votingSession.setVotesFavor(votesFavor);
        votingSession.setVotesAgainst(votesAgainst);
        votingSession.setStatus("FECHADA");

        repository.save(votingSession);
        // Retorna a sessão de votação atualizada
        return votingSession;
    }

}