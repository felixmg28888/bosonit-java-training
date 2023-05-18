package com.bosonit.formacion.block1202mongodb.persona.controller;

import com.bosonit.formacion.block1202mongodb.persona.application.PersonaService;
import com.bosonit.formacion.block1202mongodb.persona.controller.dto.personaInputDto.PersonaInputDto;
import com.bosonit.formacion.block1202mongodb.persona.controller.dto.personaOutputDto.PersonaOutputDto;
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
    public ResponseEntity<PersonaOutputDto> getPersonaById(@PathVariable String id) {
        return new ResponseEntity<>(personaService.getPersonaById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllPersonas(Integer page, Integer size) {
        try {
            List<PersonaOutputDto> personaOutputDtoList = personaService.getAllPersonas(page, size);
            if (personaOutputDtoList.isEmpty()) {
                throw new Exception("Lista vacía.");
            }
            return new ResponseEntity<>(personaOutputDtoList, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaOutputDto> updatePersona(@PathVariable("id") String id, @RequestBody PersonaInputDto personaInputDto) {
        return new ResponseEntity<>(personaService.updatePersona(id, personaInputDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removePersonaById(@PathVariable("id") String id) {
        return new ResponseEntity<>(personaService.removePersonaById(id), HttpStatus.OK);
    }
}




