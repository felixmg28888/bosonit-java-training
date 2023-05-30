package com.bosonit.formacion.block1601springappviajecliente.cliente.application;

import com.bosonit.formacion.block1601springappviajecliente.cliente.controller.dto.ClienteInputDto;
import com.bosonit.formacion.block1601springappviajecliente.cliente.controller.dto.ClienteOutputDto;
import com.bosonit.formacion.block1601springappviajecliente.cliente.domain.Cliente;
import com.bosonit.formacion.block1601springappviajecliente.cliente.repository.ClienteRepository;
import com.bosonit.formacion.block1601springappviajecliente.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public static final String CLIENTE_NO_ENCONTRADO = "Cliente no encontrado.";

    @Override
    public ClienteOutputDto addCliente(ClienteInputDto clienteInputDto) {
        Cliente cliente = new Cliente();
        cliente.setNombre(clienteInputDto.getNombre());
        cliente.setApellidos(clienteInputDto.getApellidos());
        cliente.setEdad(clienteInputDto.getEdad());
        cliente.setEmail(clienteInputDto.getEmail());
        cliente.setTelefono(clienteInputDto.getTelefono());

        clienteRepository.save(cliente);

        return new ClienteOutputDto(cliente);
    }

    @Override
    public ClienteOutputDto getClienteById(Integer idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(
                () -> new EntityNotFoundException(CLIENTE_NO_ENCONTRADO));

        return new ClienteOutputDto(cliente);
    }

    @Override
    public List<ClienteOutputDto> getAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();

        return clientes.stream().map(ClienteOutputDto::new).toList();
    }

    @Override
    public ClienteOutputDto updateCliente(Integer idCliente, ClienteInputDto clienteInputDto) {
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(
                () -> new EntityNotFoundException(CLIENTE_NO_ENCONTRADO));

        if (clienteInputDto.getNombre() != null) cliente.setNombre(clienteInputDto.getNombre());
        if (clienteInputDto.getApellidos() != null) cliente.setApellidos(clienteInputDto.getApellidos());
        if (clienteInputDto.getEdad() != null) cliente.setEdad(clienteInputDto.getEdad());
        if (clienteInputDto.getEmail() != null) cliente.setEmail(clienteInputDto.getEmail());
        if (clienteInputDto.getTelefono() != null) cliente.setTelefono(clienteInputDto.getTelefono());


        //Guardo los cambios
        clienteRepository.save(cliente);

        return new ClienteOutputDto(cliente);
    }

    @Override
    public String deleteCliente(Integer idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(
                () -> new EntityNotFoundException(CLIENTE_NO_ENCONTRADO));
        clienteRepository.delete(cliente);
        return "El cliente con id " + idCliente + " ha sido borrad@.";    }
}
