package com.bosonit.formacion.block13testingcrud.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class CustomError {
    private Date timeStamp;
    private int httpCode;
    private String mensaje;
}
