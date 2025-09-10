package com.nttdata.dockerized.postgresql.controller.exception;

import com.nttdata.dockerized.postgresql.exception.BadRequestException;
import com.nttdata.dockerized.postgresql.exception.InternalServerErrorException;
import com.nttdata.dockerized.postgresql.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Error> handleExceptionNotExists(ResourceNotFoundException ex){
        Error error = new Error("not-exists", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Error> handleBadRequest(BadRequestException ex){
        Error error = new Error("bad-request", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<Error> handleInternalServer(InternalServerErrorException ex){
        Error error = new Error("internal-server-error", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Error>> handleException(MethodArgumentNotValidException ex) {
        List<Error> errors = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.add(new Error(error.getField(), error.getDefaultMessage()));
        });

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(Exception ex) {
        Error error = new Error("unknow", ex.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }
}
