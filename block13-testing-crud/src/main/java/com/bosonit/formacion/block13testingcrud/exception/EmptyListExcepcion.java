package com.bosonit.formacion.block13testingcrud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class EmptyListExcepcion extends RuntimeException {
    public EmptyListExcepcion(String message) {
        super(message);
    }
}
