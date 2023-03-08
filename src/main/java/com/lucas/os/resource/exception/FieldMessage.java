package com.lucas.os.resource.exception;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter @Setter
public class FieldMessage implements Serializable {

    private String fieldName;
    private String message;

    public FieldMessage(String fieldName, String message) {
        super();
        this.fieldName = fieldName;
        this.message = message;
    }

    public FieldMessage() {
        super();
    }
}
