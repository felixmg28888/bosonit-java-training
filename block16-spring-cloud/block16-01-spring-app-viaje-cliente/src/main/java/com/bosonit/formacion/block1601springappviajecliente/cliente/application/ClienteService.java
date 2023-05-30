package com.bosonit.formacion.block1601springappviajecliente.cliente.application;

import com.bosonit.formacion.block1601springappviajecliente.cliente.controller.dto.ClienteInputDto;
import com.bosonit.formacion.block1601springappviajecliente.cliente.controller.dto.ClienteOutputDto;

import java.util.List;

public interface ClienteService {
    ClienteOutputDto addCliente(ClienteInputDto clienteInputDto);

    ClienteOutputDto getClienteById(Integer idCliente);


    List<ClienteOutputDto> getAllClientes();

    ClienteOutputDto updateCliente(Integer idCliente, ClienteInputDto personaInputDto);

    String deleteCliente(Integer id);
}
