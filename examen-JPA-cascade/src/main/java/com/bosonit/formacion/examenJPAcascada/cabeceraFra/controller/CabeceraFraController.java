package com.bosonit.formacion.examenJPAcascada.cabeceraFra.controller;

import com.bosonit.formacion.examenJPAcascada.cabeceraFra.application.CabeceraFraService;
import com.bosonit.formacion.examenJPAcascada.cabeceraFra.controller.dto.CabeceraFraInputDto;
import com.bosonit.formacion.examenJPAcascada.cabeceraFra.controller.dto.CabeceraFraOutputDto;
import com.bosonit.formacion.examenJPAcascada.lineasFra.controller.dto.LineaFraInputDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cabeceraFra")
public class CabeceraFraController {
    @Autowired
    CabeceraFraService cabeceraFraService;

    @PostMapping
    public ResponseEntity<CabeceraFraOutputDto> addCabeceraFra(@RequestBody CabeceraFraInputDto cabeceraFraInputDto) {
        return new ResponseEntity<>(cabeceraFraService.addCabeceraFra(cabeceraFraInputDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CabeceraFraOutputDto> getCabeceraFraById(@PathVariable Integer id) {
        return new ResponseEntity<>(cabeceraFraService.getCabeceraFraById(id), HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<?> getAllCabeceraFra() {
        try {
            List<CabeceraFraOutputDto> cabeceraFraOutputDtoList = cabeceraFraService.getAllCabeceraFra();
            if (cabeceraFraOutputDtoList.isEmpty()) {
                throw new Exception("Lista vacía.");
            }
            return new ResponseEntity<>(cabeceraFraService.getAllCabeceraFra(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CabeceraFraOutputDto> updateCabeceraFra(@PathVariable("id") Integer id, @RequestBody CabeceraFraInputDto cabeceraFraInputDto) {
        return new ResponseEntity<>(cabeceraFraService.updateCabeceraFra(id, cabeceraFraInputDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCabeceraFra(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(cabeceraFraService.deleteCabeceraFraById(id), HttpStatus.OK);

    }

    @PostMapping("/assignLineaFraToCabeceraFra")
    public ResponseEntity<CabeceraFraOutputDto> addLineaFraToCabeceraFras(@Valid @RequestBody LineaFraInputDto lineaFraInputDto) {
        return new ResponseEntity<>(cabeceraFraService.addLineaFraToCabeceraFra(lineaFraInputDto), HttpStatus.OK);
    }
}
