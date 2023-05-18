package com.bosonit.formacion.block1201criteriabuilder.persona.controller;

import com.bosonit.formacion.block1201criteriabuilder.exception.EntityNotFoundException;
import com.bosonit.formacion.block1201criteriabuilder.exception.UnprocessableEntityException;
import com.bosonit.formacion.block1201criteriabuilder.persona.application.FeignProfesor;
import com.bosonit.formacion.block1201criteriabuilder.persona.application.PersonaService;
import com.bosonit.formacion.block1201criteriabuilder.persona.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block1201criteriabuilder.persona.controller.dto.PersonaOutputDto;
import com.bosonit.formacion.block1201criteriabuilder.profesor.controller.dto.ProfesorOutputDto;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    PersonaService personaService;
    @Autowired
    FeignProfesor feignProfesor;


    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody PersonaInputDto personaInputDto) {
        return new ResponseEntity<>(personaService.addPersona(personaInputDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaOutputDto> getPersonasById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(personaService.getPersonaById(id), HttpStatus.OK);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> getPersonasByName(@PathVariable("nombre") String nombre) {
        try {
            List<PersonaOutputDto> personaOutputDtoList = personaService.getPersonasByName(nombre);
            if (personaOutputDtoList.isEmpty()) {
                throw new Exception("Persona no encontrada.");
            }
            return new ResponseEntity<>(personaOutputDtoList, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listaPersonas() {
        try {
            List<PersonaOutputDto> personaOutputDtoList = personaService.getAllPersonas();
            if (personaOutputDtoList.isEmpty()) {
                throw new Exception("Lista vacía.");
            }
            return new ResponseEntity<>(personaOutputDtoList, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaOutputDto> updatePersona(@PathVariable("id") Integer id, @RequestBody PersonaInputDto personaInputDto) {
        return new ResponseEntity<>(personaService.updatePersona(id, personaInputDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borraPersonaById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(personaService.deletePersona(id), HttpStatus.OK);
    }


    //------------------------------------FEIGN & RESTTEMPLATE-------------------------------------
    @GetMapping("/profesor/feign/{id}")
    public ResponseEntity<ProfesorOutputDto> getProfesorFeign(@PathVariable Integer id) {
        ResponseEntity<ProfesorOutputDto> responseEntity;
        try {
            responseEntity = new ResponseEntity(feignProfesor.getProfesorById(id), HttpStatus.OK);
        } catch (FeignException.NotFound e) {
            throw new EntityNotFoundException("Profesor (Feign) no encontrado");
        }
        return new ResponseEntity<>(feignProfesor.getProfesorById(id), HttpStatus.OK);
    }

    @GetMapping("/profesor/restTemplate/{id}")
    public ResponseEntity<ProfesorOutputDto> getProfesorRestTemplate(@PathVariable Integer id) {
        ResponseEntity<ProfesorOutputDto> responseEntity;
        try {
            responseEntity = new RestTemplate().getForEntity("http://localhost:8080/profesor/{id}", ProfesorOutputDto.class, id);
        } catch (HttpClientErrorException.NotFound e) {
            throw new EntityNotFoundException("Profesor (RestTemplate) no encontrado");
        }

        return responseEntity;
    }
    //---------------------------------------------------------------------------------------------

    //----------------------------------------CORS-------------------------------------------------
    @CrossOrigin(origins = "https://cdpn.io/")
    @PostMapping("/addperson")
    public PersonaOutputDto addPerson(@RequestBody PersonaInputDto personaInputDto) throws UnprocessableEntityException {
        return personaService.addPersona(personaInputDto);
    }

    @CrossOrigin(origins = "https://cdpn.io/")
    @GetMapping("/getall")
    public ResponseEntity<List<PersonaOutputDto>> getAllCors() {
        return ResponseEntity.ok().body(personaService.getAllPersonas());
    }
    //---------------------------------------------------------------------------------------------
    //--------------------------------CRITERIA BUILDER---------------------------------------------



    @GetMapping("/get")
    public ResponseEntity<List<PersonaOutputDto>> getPersonas(@RequestParam(required = false, name = "usuario") String usuario,
                                                              @RequestParam(required = false, name = "name") String name,
                                                              @RequestParam(required = false, name = "surname") String surname,
                                                              @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date createdDate,
                                                              @RequestParam(required = false) String dateCondition,
                                                              @RequestParam(required = false) String orderBy,
                                                              @RequestParam Integer page,
                                                              @RequestParam(required = false) Integer size) {
        return ResponseEntity.ok().body(personaService.getPersonas(usuario, name, surname, createdDate, dateCondition, orderBy,page,size));
    }

    //---------------------------------------------------------------------------------------------




}