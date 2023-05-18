package com.bosonit.formacion.examenJPAcascada.lineasFra.controller;


import com.bosonit.formacion.examenJPAcascada.lineasFra.application.LineaFraService;
import com.bosonit.formacion.examenJPAcascada.lineasFra.controller.dto.LineaFraInputDto;
import com.bosonit.formacion.examenJPAcascada.lineasFra.controller.dto.LineaFraOuputDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lineaFra")
public class LineaFraController {
    @Autowired
    LineaFraService lineaFraService;

    @PostMapping
    public ResponseEntity<LineaFraOuputDto> addLineaFra (@Valid @RequestBody LineaFraInputDto lineaFraInputDto) {
        return new ResponseEntity<>(lineaFraService.addLineaFra(lineaFraInputDto), HttpStatus.CREATED);

    }
    @GetMapping("/{id}")
    public ResponseEntity<LineaFraOuputDto> getLineaFraById(@PathVariable Integer id) {
        return new ResponseEntity<>(lineaFraService.getLineaFraById(id), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<?> getAllLineasFra() {
        try {
            List<LineaFraOuputDto> lineaFraOuputDtoList = lineaFraService.getAllLineasFra();
            if (lineaFraOuputDtoList.isEmpty()) {
                throw new Exception("Lista vacía.");
            }
            return new ResponseEntity<>(lineaFraService.getAllLineasFra(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<LineaFraOuputDto> updateLineaFra(@PathVariable("id") Integer id, @RequestBody LineaFraInputDto lineaFraInputDto) {
        return new ResponseEntity<>(lineaFraService.updateLineaFra(id, lineaFraInputDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLineaFra(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(lineaFraService.deleteLineaFraById(id), HttpStatus.OK);
    }
}


