package com.bosonit.formacion.block13testingcrud.persona.controller;

import com.bosonit.formacion.block13testingcrud.persona.application.PersonaServiceImpl;
import com.bosonit.formacion.block13testingcrud.persona.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block13testingcrud.persona.controller.dto.PersonaOutputDto;
import com.bosonit.formacion.block13testingcrud.persona.domain.Persona;
import com.bosonit.formacion.block13testingcrud.persona.repository.PersonaRepository;
import com.bosonit.formacion.block13testingcrud.profesor.controller.dto.ProfesorOutputDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonaControllerTest {
    @Mock
    PersonaRepository personaRepository;
    @Mock
    PersonaServiceImpl personaServiceImpl;

    @InjectMocks
    PersonaController personaController;

    private PersonaInputDto personaInputDto;
    private Persona persona;
    private PersonaOutputDto personaOutputDto;

    private List<PersonaOutputDto> personaOutputDtoList = new ArrayList<>();

    private ProfesorOutputDto profesorOutputDto;


    @BeforeEach
    void setUp() {
        personaInputDto = new PersonaInputDto();
        personaInputDto.setUsuario("Persona1");
        personaInputDto.setPassword("PassPersona1");
        personaInputDto.setName("Persona1");
        personaInputDto.setSurname("Persona1");
        personaInputDto.setCompanyEmail("Persona1@companyEmail.com");
        personaInputDto.setPersonalEmail("Persona1@personalEmaial");
        personaInputDto.setCity("CitiyPersona1");
        personaInputDto.setActive(true);
        personaInputDto.setImagenUrl("imagenPesona1");

        persona = new Persona();
        persona.setIdPersona(1);
        persona.setUsuario(personaInputDto.getUsuario());
        persona.setPassword(personaInputDto.getPassword());
        persona.setName(personaInputDto.getName());
        persona.setSurname(personaInputDto.getSurname());
        persona.setCompanyEmail(personaInputDto.getCompanyEmail());
        persona.setPersonalEmail(personaInputDto.getPersonalEmail());
        persona.setCity(personaInputDto.getCity());
        persona.setActive(personaInputDto.getActive());
        persona.setImagenUrl(personaInputDto.getImagenUrl());
        persona.setCreatedDate(new Date());



        personaOutputDto = new PersonaOutputDto(persona);

        personaOutputDtoList.add(personaOutputDto);


        profesorOutputDto = new ProfesorOutputDto();
        profesorOutputDto.setIdProfesor(1);


    }

    @Test
    void addPersona() {
        //Given -
        when(personaServiceImpl.addPersona(personaInputDto)).thenReturn(personaOutputDto);

        //When -
        ResponseEntity<PersonaOutputDto> personaOutputDtoResponseEntity = personaController.addPersona(personaInputDto);

        //Then -
        assertEquals(HttpStatus.CREATED, personaOutputDtoResponseEntity.getStatusCode());
    }

    @Test
    void getPersonasById() {
        //Given -
        when(personaServiceImpl.getPersonaById(1)).thenReturn(personaOutputDto);

        //When -
        ResponseEntity<PersonaOutputDto> personaOutputDtoResponseEntity = personaController.getPersonasById(1);

        //Then -
        assertEquals(HttpStatus.OK, personaOutputDtoResponseEntity.getStatusCode());
    }

    @Test
    void getPersonasByName() {
        //Given
        when(personaServiceImpl.getPersonasByName("Persona1")).thenReturn(personaOutputDtoList);

        //When -
        ResponseEntity<?> personaOutputDtoResponseEntity = personaController.getPersonasByName("Persona1");

        //Then -
        assertEquals(HttpStatus.OK, personaOutputDtoResponseEntity.getStatusCode());
    }

    @Test
    void listaPersonas() {
        //Given
        when(personaServiceImpl.getAllPersonas()).thenReturn(personaOutputDtoList);

        //When -
        ResponseEntity<?> personaOutputDtoResponseEntity = personaController.listaPersonas();

        //Then -
        assertEquals(HttpStatus.OK, personaOutputDtoResponseEntity.getStatusCode());

    }

    @Test
    void updatePersona() {
        //Given -
        personaInputDto.setUsuario("Nombre Actualizado");
        when(personaServiceImpl.updatePersona(1, personaInputDto)).thenReturn(personaOutputDto);

        //When -
        ResponseEntity<PersonaOutputDto> personaOutputDtoResponseEntity = personaController.updatePersona(1, personaInputDto);

        //Then -
        assertEquals(HttpStatus.OK, personaOutputDtoResponseEntity.getStatusCode());
    }

    @Test
    void borraPersonaById() {
        //Given -
        when(personaServiceImpl.deletePersona(1)).thenReturn("Persona borrada con éxito");

        //When -
        ResponseEntity<String> personaOutputDtoResponseEntity = personaController.borraPersonaById(1);

        //Then -
        assertEquals(HttpStatus.OK, personaOutputDtoResponseEntity.getStatusCode());
        assertEquals("Persona borrada con éxito", personaOutputDtoResponseEntity.getBody());
    }
}