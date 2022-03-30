package com.vinicius.cooperativevotes.controller.v1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vinicius.cooperativevotes.model.Agenda;
import com.vinicius.cooperativevotes.service.AgendaService;
import com.vinicius.cooperativevotes.dto.AgendaRequestDto;
import com.vinicius.cooperativevotes.dto.AgendaResponseDto;

import static org.springframework.http.HttpStatus.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/agenda")
public class AgendaController {

    private final AgendaService service;

    @Autowired
    public AgendaController(AgendaService service) {
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

    // Cria uma nova pauta
    @ResponseStatus(CREATED)
    @PostMapping()
    public Agenda create(@Validated @RequestBody AgendaRequestDto agenda) {
        return service.save(agenda);
    }

    // Retorna uma pauta filtrando pelo id
    @GetMapping("/{id}")
    public AgendaResponseDto get(@PathVariable String id){
        return service.get(id);
    }

    // Retorna uma lista de pautas
    @GetMapping("")
    @ResponseStatus(OK)
    public List<Agenda> getAll() {
        return service.getAll();
    }

    // Atualiza uma pauta filtrando pelo id
    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public Agenda update(@Validated @RequestBody AgendaRequestDto agenda, @PathVariable String id){
        return service.update(agenda, id);
    }

    // Deleta uma pauta filtrando pelo id
    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

}