package com.bosonit.formacion.examenJPAcascada.cliente.controller.dto;


import com.bosonit.formacion.examenJPAcascada.cliente.domain.Cliente;
import lombok.Getter;

@Getter
public class ClienteOutputDto {

    private Integer idCliente;
    private String nombreCliente;

    public ClienteOutputDto (Cliente cliente){
        this.idCliente=cliente.getIdCliente();
        this.nombreCliente=cliente.getNombreCliente();
    }

}
