package com.vinicius.cooperativevotes.controller.v1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vinicius.cooperativevotes.service.VoteService;
import com.vinicius.cooperativevotes.dto.VoteDto;

import static org.springframework.http.HttpStatus.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/vote")
public class VoteController {

    private final VoteService service;

    @Autowired
    public VoteController(VoteService service) {
        this.service = service;
    }

    // Tratamento de exception dos campos obrigatórios das requesições desta classe
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    // Cria um voto em uma sessão de votação
    @ResponseStatus(CREATED)
    @PostMapping()
    public VoteDto create(@Validated @RequestBody VoteDto vote) {
        return service.save(vote);
    }

    // Retorna todos os votos de uma sessão de votação
    @GetMapping("/voting_session/{id_voting_session}")
    @ResponseStatus(OK)
    public List<VoteDto> getAllVotingSession(@PathVariable String idVotingSession) {
        return service.getAllVotingSession(idVotingSession);
    }

    // Retorna todos os votos de um cpf
    @GetMapping("/{cpf}")
    @ResponseStatus(OK)
    public List<VoteDto> getAllByCpf(@PathVariable String cpf){
        return service.getAllByCpf(cpf);
    }
}