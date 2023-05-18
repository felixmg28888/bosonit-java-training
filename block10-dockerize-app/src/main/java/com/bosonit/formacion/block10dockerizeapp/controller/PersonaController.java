package com.bosonit.formacion.block10dockerizeapp.controller;

import com.bosonit.formacion.block10dockerizeapp.application.PersonaService;
import com.bosonit.formacion.block10dockerizeapp.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block10dockerizeapp.controller.dto.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    PersonaService personaService;

    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody PersonaInputDto personaInputDto) {
        return new ResponseEntity<>(personaService.addPersona(personaInputDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaOutputDto> getPersonaById(@PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(personaService.getPersonaById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllPersonas() {
        try {
            return new ResponseEntity<>(personaService.getAllPersonas(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND + " - Persona no encontrada");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaOutputDto> updatePersona(@PathVariable("id") Integer id, @RequestBody PersonaInputDto personaInputDto) throws Exception {
        return new ResponseEntity<>(personaService.updatePersona(id, personaInputDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePersonaById(@PathVariable("id") Integer id) throws Exception {
        return new ResponseEntity<>(personaService.deletePersonaById(id), HttpStatus.OK);
    }
}


