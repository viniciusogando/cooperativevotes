package com.vinicius.cooperativevotes.controller.v1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vinicius.cooperativevotes.service.VotingSessionService;
import com.vinicius.cooperativevotes.dto.VotingSessionRequestDto;
import com.vinicius.cooperativevotes.dto.VotingSessionResponseDto;

import static org.springframework.http.HttpStatus.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/voting_session")
public class VotingSessionController {

    private final VotingSessionService service;

    @Autowired
    public VotingSessionController(VotingSessionService service) {
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

    // Cria uma nova sessão de votação para uma pauta
    @ResponseStatus(CREATED)
    @PostMapping()
    public VotingSessionResponseDto create(@Validated @RequestBody VotingSessionRequestDto votingSession) {
        return service.save(votingSession);
    }

    // Retorna uma sessão de votação filtrando pelo id
    @GetMapping("/{id}")
    public VotingSessionResponseDto get(@PathVariable String id){
        return service.get(id);
    }

    // Retorna todas as sessões de votações
    @GetMapping("")
    @ResponseStatus(OK)
    public List<VotingSessionResponseDto> getAll() {
        return service.getAll();
    }

    // Encerra uma sessão de votação filtrando pelo id
    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable String id) {
        service.close(id);
    }

}