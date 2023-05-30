package com.bosonit.formacion.block1601springappviajecliente.cliente.controller.dto;

import com.bosonit.formacion.block1601springappviajecliente.cliente.domain.Cliente;
import lombok.Getter;

@Getter
public class ClienteOutputDto {
    private Integer idCliente;
    private String nombre;
    private String apellidos;
    private Integer edad;
    private String email;
    private String telefono;


    public ClienteOutputDto(Cliente cliente) {
        this.idCliente = cliente.getIdCliente();
        this.nombre = cliente.getNombre();
        this.apellidos = cliente.getApellidos();
        this.edad = cliente.getEdad();
        this.email = cliente.getEmail();
        this.telefono = cliente.getTelefono();

    }
}
