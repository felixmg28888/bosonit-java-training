package com.bosonit.formacion;

public class InvalidLineFormatException extends Exception {

    public InvalidLineFormatException(){}
    public InvalidLineFormatException(String mensajeError) {
        super(mensajeError);
    }
}
