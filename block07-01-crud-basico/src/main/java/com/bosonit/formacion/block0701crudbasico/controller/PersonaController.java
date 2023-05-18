package com.bosonit.formacion.block0701crudbasico.controller;

import com.bosonit.formacion.block0701crudbasico.application.PersonaServiceImpl;
import com.bosonit.formacion.block0701crudbasico.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block0701crudbasico.controller.dto.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    PersonaServiceImpl personaService;


    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody PersonaInputDto personaInputDto) {
        return new ResponseEntity<>(personaService.addPersona(personaInputDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePersona(@PathVariable("id") Integer id, @RequestBody PersonaInputDto personaInputDto) {
        try {
            return new ResponseEntity<>(personaService.updatePersona(id, personaInputDto), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND + " - Persona no encontrada");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borraPersonaById(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(personaService.deletePersona(id), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND + " - Persona no encontrada");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonasById(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(personaService.getPersonaById(id), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND + " - Persona no encontrada");
        }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> getPersonasByName(@PathVariable("nombre") String nombre) {
        List<PersonaOutputDto> personaOutputDtoList = personaService.getPersonasByName(nombre);
        if (personaOutputDtoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND + " - Persona no encontrada");
        }
        return new ResponseEntity<>(personaOutputDtoList, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> listaPersonas() {
        List<PersonaOutputDto> personaOutputDtoList = personaService.getAllPersonas();
        if (personaOutputDtoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND + " - Lista vacía");
        }
        return new ResponseEntity<>(personaOutputDtoList, HttpStatus.OK);
    }
}