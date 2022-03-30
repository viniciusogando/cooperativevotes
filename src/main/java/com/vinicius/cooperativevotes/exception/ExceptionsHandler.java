package com.vinicius.cooperativevotes.exception;

import com.vinicius.cooperativevotes.model.Exception;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(value = NotFoundException.class)
    protected ResponseEntity<Exception> handleNotFoundException(NotFoundException ex){
        return new ResponseEntity<>(new Exception(ex.getMessage()), NOT_FOUND);
    }

    @ExceptionHandler(value = BadRequestException.class)
    protected ResponseEntity<Exception> handleBadRequestException(BadRequestException ex){
        return new ResponseEntity<>(new Exception(ex.getMessage()), BAD_REQUEST);
    }
}
