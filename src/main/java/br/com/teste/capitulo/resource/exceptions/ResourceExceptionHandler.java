package br.com.teste.capitulo.resource.exceptions;

import br.com.teste.capitulo.service.exceptions.DBException;
import br.com.teste.capitulo.service.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ValidationError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        ValidationError err = new ValidationError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DBException.class)
    public ResponseEntity<ValidationError> database(DBException e, HttpServletRequest request) {
        String error = "Database error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ValidationError err = new ValidationError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> database(MethodArgumentNotValidException e, HttpServletRequest request) {
        String error = "data validation error";
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError err = new ValidationError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        for(FieldError fieldMessages : e.getBindingResult().getFieldErrors()){
            err.addErros(fieldMessages.getField(), fieldMessages.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(err);
    }
}
