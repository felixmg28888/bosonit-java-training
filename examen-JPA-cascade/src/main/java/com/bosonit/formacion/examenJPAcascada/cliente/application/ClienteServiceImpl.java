package com.bosonit.formacion.examenJPAcascada.cliente.application;

import com.bosonit.formacion.examenJPAcascada.cliente.controller.dto.ClienteInputDto;
import com.bosonit.formacion.examenJPAcascada.cliente.controller.dto.ClienteOutputDto;
import com.bosonit.formacion.examenJPAcascada.cliente.domain.Cliente;
import com.bosonit.formacion.examenJPAcascada.cliente.repository.ClienteRepository;
import com.bosonit.formacion.examenJPAcascada.exception.EntityNotFoundException;
import com.bosonit.formacion.examenJPAcascada.exception.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public ClienteOutputDto addCliente(ClienteInputDto clienteInputDto) {

        //--------------------Control clienteInputDto----------------------------------------------
        if (clienteInputDto.getNombreCliente() == null) {
            throw new UnprocessableEntityException(" nombreCliente no puede ser nulo.");
        }
        //-----------------------------------------------------------------------------------------

        Cliente cliente = new Cliente();
        cliente.setNombreCliente(clienteInputDto.getNombreCliente());

        clienteRepository.save(cliente);

        return new ClienteOutputDto(cliente);
    }


    @Override
    public ClienteOutputDto getClienteById(Integer id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado."));
        return new ClienteOutputDto(cliente);
    }


    @Override
    public List<ClienteOutputDto> getAllClientes() {
//-------------------------------------Opción 1----------------------------------------
        List<ClienteOutputDto> clienteOutputDtoList = new ArrayList<>();
        for (Cliente c : clienteRepository.findAll()) {
            clienteOutputDtoList.add(new ClienteOutputDto(c));
        }
        return clienteOutputDtoList;
    }
//-------------------------------------Opción 2----------------------------------------
//        List <Cliente> clienteList= clienteRepository.findAll();
//        return clienteList.stream().map(ClienteOutputDto::new).toList();
//    }
//-------------------------------------------------------------------------------------

    @Override
    public ClienteOutputDto updateCliente(Integer id, ClienteInputDto clienteInputDto) {

        //--------------------Control clienteInputDto----------------------------------------------
        if (clienteInputDto.getNombreCliente() == null) {
            throw new UnprocessableEntityException(" nombreCliente no puede ser nulo.");
        }
        //-----------------------------------------------------------------------------------------

        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado."));
        cliente.setNombreCliente(clienteInputDto.getNombreCliente());
        clienteRepository.save(cliente);
        return new ClienteOutputDto(cliente);
    }

    @Override
    public String deleteClienteById(Integer id) {
        Cliente cliente= clienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado."));
        clienteRepository.delete(cliente);
        return "El cliente con id " + id + " ha sido borrad@.";
    }
}
