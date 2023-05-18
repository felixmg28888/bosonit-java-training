package com.bosonit.formacion.examenJPAcascada.cliente.application;

import com.bosonit.formacion.examenJPAcascada.cliente.controller.dto.ClienteInputDto;
import com.bosonit.formacion.examenJPAcascada.cliente.controller.dto.ClienteOutputDto;

import java.util.List;

public interface ClienteService {
    ClienteOutputDto addCliente (ClienteInputDto clienteInputDto);
    ClienteOutputDto getClienteById (Integer id);
    List<ClienteOutputDto> getAllClientes ();
    ClienteOutputDto updateCliente (Integer id, ClienteInputDto clienteInputDto);
    String deleteClienteById (Integer id);
}
