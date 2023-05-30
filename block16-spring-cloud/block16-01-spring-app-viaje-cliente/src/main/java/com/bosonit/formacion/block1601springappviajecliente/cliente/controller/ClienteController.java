package com.bosonit.formacion.block1601springappviajecliente.cliente.controller;

import com.bosonit.formacion.block1601springappviajecliente.cliente.application.ClienteService;
import com.bosonit.formacion.block1601springappviajecliente.cliente.controller.dto.ClienteInputDto;
import com.bosonit.formacion.block1601springappviajecliente.cliente.controller.dto.ClienteOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteOutputDto> addCliente(@RequestBody ClienteInputDto clienteInputDto) {
        return new ResponseEntity<>(clienteService.addCliente(clienteInputDto), HttpStatus.CREATED);
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<ClienteOutputDto> getClienteById(@PathVariable("idCliente") Integer idCliente) {
        return new ResponseEntity<>(clienteService.getClienteById(idCliente), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<Object> listaClientes() {
        try {
            List<ClienteOutputDto> clienteOutputDtoList = clienteService.getAllClientes();
            if (clienteOutputDtoList.isEmpty()) {
                throw new Exception("Lista vacía.");
            }
            return new ResponseEntity<>(clienteOutputDtoList, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<ClienteOutputDto> updateCliente(@PathVariable("idCliente") Integer idCliente, @RequestBody ClienteInputDto clienteInputDto) {
        return new ResponseEntity<>(clienteService.updateCliente(idCliente, clienteInputDto), HttpStatus.OK);
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<String> borraClienteById(@PathVariable("idCliente") Integer idCliente) {
        return new ResponseEntity<>(clienteService.deleteCliente(idCliente), HttpStatus.OK);
    }
}
