package com.bosonit.formacion.block070202crudvalidation.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class CustomError {
    private Date timeStamp;
    private int HttpCode;
    private String mensaje;
}
