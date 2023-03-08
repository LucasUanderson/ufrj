package com.lucas.os.resource.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class StandardError implements Serializable {

    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String path;

}



