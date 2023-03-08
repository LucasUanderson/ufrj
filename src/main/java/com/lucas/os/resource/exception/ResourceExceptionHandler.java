package com.lucas.os.resource.exception;

import com.lucas.os.service.exception.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException ex, HttpServletRequest resquest){
        StandardError error =
                new StandardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(), resquest.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> objectNotFound(MethodArgumentNotValidException ex, HttpServletRequest resquest){
       ValidationError error = new ValidationError(LocalDateTime.now(),HttpStatus.BAD_REQUEST.value(),"Erro na validação dos campos!", resquest.getRequestURI());

       for(FieldError x : ex.getBindingResult().getFieldErrors()){
           error.addError(x.getField(), x.getDefaultMessage());
       }

       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }



}
