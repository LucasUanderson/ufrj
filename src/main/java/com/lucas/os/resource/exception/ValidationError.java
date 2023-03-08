package com.lucas.os.resource.exception;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@NoArgsConstructor
public class ValidationError extends StandardError {

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(LocalDateTime timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message) {
        this.errors.add(new FieldMessage(fieldName, message));
    }

}
