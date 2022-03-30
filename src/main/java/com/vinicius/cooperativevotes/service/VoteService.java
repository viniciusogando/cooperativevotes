package com.vinicius.cooperativevotes.service;

import com.vinicius.cooperativevotes.exception.BadRequestException;
import com.vinicius.cooperativevotes.exception.NotFoundException;
import com.vinicius.cooperativevotes.model.Vote;
import com.vinicius.cooperativevotes.model.VotingSession;
import com.vinicius.cooperativevotes.repository.VoteRepository;
import com.vinicius.cooperativevotes.repository.VotingSessionRepository;
import com.vinicius.cooperativevotes.dto.VoteDto;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class VoteService {

    private final VoteRepository repository;
    private final VotingSessionRepository votingSessionRepository;
    private final VotingSessionService votingSessionService;
    private final CpfService cpfService;

    // Cria um novo voto em uma sessão de votação
    public VoteDto save(VoteDto voteDto) {
        String votingSessionId = voteDto.getVotingSessionId();
        // Verifica se a sessão de votação existe, se não retorna erro
        VotingSession votingSession = votingSessionRepository.findById(votingSessionId).
                orElseThrow(() -> new NotFoundException("Sessão de votação {" + votingSessionId + "} não encontrada"));

        // Verifica se o cfp informado é valido
        String cpfAbleToVote = cpfService.cpfAbleToVote(voteDto.getCpf());
        if (!Objects.equals(cpfAbleToVote, "")) {
            throw new BadRequestException(cpfAbleToVote);
        }

        // Valida se a sessão de votação não devia estar fechada
        votingSession = votingSessionService.validateVotingSession(votingSession);
        String votingSessionStatus = votingSession.getStatus();

        // Se a sessão de votação não estiver aberta retorna um erro
        if (!Objects.equals(votingSessionStatus, "ABERTA")) {
            throw new BadRequestException("A sessão de votação {" + votingSessionId + "} está " + votingSessionStatus);
        }

        String voteCpf = voteDto.getCpf();
        // Verifica se o cpf informado não tem voto resgitrado na sessão de votação, se sim retorna um erro
        List<Vote> votes = repository.findByCpfAndVotingSession(voteCpf, votingSession);
        if (votes.size() > 0) {
            throw new BadRequestException("O CPF {" + voteCpf + "} já votou na sessão de votação {" + votingSessionId + "}");
        }

        // Salva o voto
        Vote vote = new Vote(voteDto.getCpf(), votingSession, voteDto.getVote());
        repository.save(vote);
        return voteDto;
    }

    // Lista todos os votos de uma sessão de votação
    public List<VoteDto> getAllVotingSession(String votingSessionId){
        // Verifica se a sessão de votação existe, se não retorna erro
        VotingSession votingSession = votingSessionRepository.findById(votingSessionId).
                orElseThrow(() -> new NotFoundException("Sessão de votação {" + votingSessionId + "} não encontrada"));

        
        // Busca todos os votos de uma sessão de votação
        List<Vote> votes = repository.findByVotingSession(votingSession);

        // Cria as VoteDto para o retorno da api
        return this.voteDtos(votes);
    }

    // Lista todos votos de um cpf específico
    public List<VoteDto> getAllByCpf(String cpf){
        // Busca todos os votos do cpf informado
        List<Vote> votes = repository.findByCpf(cpf);
        
        // Cria as VoteDto para o retorno da api
        return this.voteDtos(votes);
    }

    // Recebe uma lista de Vote e retorna uma lista de VoteDto
    public List<VoteDto> voteDtos(List<Vote> votes) {
        List<VoteDto> voteDtos = new ArrayList<VoteDto>();

        for (Vote vote: votes) {
            VoteDto voteDto = new VoteDto();
            voteDto.setVotingSessionId(vote.getVotingSession().getId().toString());
            voteDto.setCpf(vote.getCpf());
            voteDto.setVote(vote.getVote());

            voteDtos.add(voteDto);
        }

        return voteDtos;
    }

}