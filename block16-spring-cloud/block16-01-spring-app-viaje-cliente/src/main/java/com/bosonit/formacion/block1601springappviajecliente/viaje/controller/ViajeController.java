package com.bosonit.formacion.block1601springappviajecliente.viaje.controller;

import com.bosonit.formacion.block1601springappviajecliente.viaje.application.ViajeService;
import com.bosonit.formacion.block1601springappviajecliente.viaje.controller.dto.ViajeInputDto;
import com.bosonit.formacion.block1601springappviajecliente.viaje.controller.dto.ViajeOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trip")
public class ViajeController {
    @Autowired
    ViajeService viajeService;

    @PostMapping
    public ResponseEntity<ViajeOutputDto> addViaje(@RequestBody ViajeInputDto viajeInputDto) {
        return new ResponseEntity<>(viajeService.addViaje(viajeInputDto), HttpStatus.CREATED);
    }

    @GetMapping("/{idViaje}")
    public ResponseEntity<ViajeOutputDto> getViajeById(@PathVariable("idViaje") Integer idViaje) {
        return new ResponseEntity<>(viajeService.getViajeById(idViaje), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<Object> listaViajes() {
        try {
            List<ViajeOutputDto> viajeOutputDtoList = viajeService.getAllViajes();
            if (viajeOutputDtoList.isEmpty()) {
                throw new Exception("Lista vacía.");
            }
            return new ResponseEntity<>(viajeOutputDtoList, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{idViaje}")
    public ResponseEntity<ViajeOutputDto> updateViaje(@PathVariable("idViaje") Integer idViaje, @RequestBody ViajeInputDto viajeInputDto) {
        return new ResponseEntity<>(viajeService.updateViaje(idViaje, viajeInputDto), HttpStatus.OK);
    }

    @DeleteMapping("/{idViaje}")
    public ResponseEntity<String> borraViajeById(@PathVariable("idViaje") Integer idViaje) {
        return new ResponseEntity<>(viajeService.deleteViaje(idViaje), HttpStatus.OK);
    }

    @PostMapping("/addPassenger/{idviaje}/{idCliente}")
    public ResponseEntity<String> addPassenger(@PathVariable("idviaje") Integer idViaje,
                                               @PathVariable("idCliente") Integer idCliente) {
        return new ResponseEntity<>(viajeService.addPassenger(idViaje, idCliente), HttpStatus.OK);
    }

    @GetMapping("/passenger/count/{idviaje}")
    public ResponseEntity<Integer> countPassengers(@PathVariable("idviaje") Integer idViaje) {
        return new ResponseEntity<>(viajeService.countPassenger(idViaje), HttpStatus.OK);
    }


    @PutMapping("/{idviaje}/{status}")
    public ResponseEntity<String> updateStatus (@PathVariable("idviaje") Integer idViaje,
                                                @PathVariable("status") String status) {
        return new ResponseEntity<>(viajeService.updateStatus(idViaje, status), HttpStatus.OK);
    }

    @GetMapping("/verify/{idviaje}")
    public ResponseEntity<String> verifyViaje (@PathVariable("idviaje") Integer idViaje) {
        return new ResponseEntity<>(viajeService.verifyViaje(idViaje), HttpStatus.OK);
    }


}
