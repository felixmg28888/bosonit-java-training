package com.bosonit.formacion.examenJPAcascada.cliente.controller;

import com.bosonit.formacion.examenJPAcascada.cliente.application.ClienteService;
import com.bosonit.formacion.examenJPAcascada.cliente.controller.dto.ClienteInputDto;
import com.bosonit.formacion.examenJPAcascada.cliente.controller.dto.ClienteOutputDto;
import jakarta.validation.Valid;
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
    public ResponseEntity<ClienteOutputDto> addCliente (@Valid @RequestBody ClienteInputDto clienteInputDto) {
        return new ResponseEntity<>(clienteService.addCliente(clienteInputDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteOutputDto> getClienteById(@PathVariable Integer id) {
        return new ResponseEntity<>(clienteService.getClienteById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllCliente() {
        try {
            List<ClienteOutputDto> clienteOutputDtoList = clienteService.getAllClientes();
            if (clienteOutputDtoList.isEmpty()) {
                throw new Exception("Lista vacía.");
            }
            return new ResponseEntity<>(clienteService.getAllClientes(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<ClienteOutputDto> updateCliente(@PathVariable("id") Integer id, @RequestBody ClienteInputDto clienteInputDto) {
        return new ResponseEntity<>(clienteService.updateCliente(id, clienteInputDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCliente(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(clienteService.deleteClienteById(id), HttpStatus.OK);
    }
}
