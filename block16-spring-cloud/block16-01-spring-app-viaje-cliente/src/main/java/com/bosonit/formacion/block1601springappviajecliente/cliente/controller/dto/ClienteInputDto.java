package com.bosonit.formacion.block1601springappviajecliente.cliente.controller.dto;

import lombok.Getter;

@Getter
public class ClienteInputDto {
    private String nombre;
    private String apellidos;
    private Integer edad;
    private String email;
    private String telefono;
}
