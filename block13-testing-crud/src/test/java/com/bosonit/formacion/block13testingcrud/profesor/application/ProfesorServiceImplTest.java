package com.bosonit.formacion.block13testingcrud.profesor.application;

import com.bosonit.formacion.block13testingcrud.persona.application.PersonaServiceImpl;
import com.bosonit.formacion.block13testingcrud.persona.domain.Persona;
import com.bosonit.formacion.block13testingcrud.persona.repository.PersonaRepository;
import com.bosonit.formacion.block13testingcrud.profesor.controller.dto.ProfesorInputDto;
import com.bosonit.formacion.block13testingcrud.profesor.controller.dto.ProfesorOutputDto;
import com.bosonit.formacion.block13testingcrud.profesor.domain.Profesor;
import com.bosonit.formacion.block13testingcrud.profesor.repository.ProfesorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfesorServiceImplTest {
    @Mock
    private ProfesorRepository profesorRepository;
    @InjectMocks
    private ProfesorServiceImpl profesorServiceImpl;

    private ProfesorInputDto profesorInputDto;
    private Profesor profesor;
    private ProfesorOutputDto profesorOutputDto;

    @Mock
    private PersonaRepository personaRepository;

    @Mock
    private PersonaServiceImpl personaServiceImpl;
    private Persona persona;

    @BeforeEach
    void setUp() {
        persona = new Persona();
        persona.setIdPersona(1);
        persona.setUsuario("Persona1");
        persona.setPassword("PassPersona1");
        persona.setName("Persona1");
        persona.setSurname("Persona1");
        persona.setCompanyEmail("Persona1@companyEmail.com");
        persona.setPersonalEmail("Persona1@personalEmaial");
        persona.setCity("CitiyPersona1");
        persona.setActive(true);
        persona.setImagenUrl("imagenPesona1");
        personaRepository.save(persona);

        profesorInputDto=new ProfesorInputDto();
        profesorInputDto.setIdPersona(persona.getIdPersona());
        profesorInputDto.setComments("Profesor1 Comments");
        profesorInputDto.setBranch("Profesor1 Branch");

        profesor= new Profesor();
        profesor.setIdProfesor(1);
        profesor.setComments(profesorInputDto.getComments());
        profesor.setBranch(profesorInputDto.getBranch());

        persona.setProfesor(profesor);
        profesor.setPersona(persona);

        personaRepository.save(persona);
        profesorRepository.save(profesor);


    }

    @Test
    void addProfesor() {
        //Given
        when(profesorRepository.save(any(Profesor.class))).thenReturn(profesor);
        when(personaRepository.findById(1)).thenReturn(Optional.ofNullable(persona));


        //When
        profesorOutputDto = profesorServiceImpl.addProfesor(profesorInputDto);

        //Then
        assertEquals(profesorOutputDto.getComments(), profesor.getComments());
        assertEquals(profesorOutputDto.getBranch(), profesorInputDto.getBranch());
    }


    @Test
    void getProfesorById() {
        //Given
        when(profesorRepository.findById(1)).thenReturn(Optional.ofNullable(profesor));

        //When
        profesorOutputDto = profesorServiceImpl.getProfesorById(1);

        //Then
        assertEquals(profesorOutputDto.getComments(), profesor.getComments());
        assertEquals(profesorOutputDto.getBranch(), profesorInputDto.getBranch());
    }

    @Test
    void getAllProfesores() {
        //Given
        when(profesorRepository.findAll()).thenReturn(List.of(profesor));

        //When
        List<ProfesorOutputDto> profesorOutputDtoList = profesorServiceImpl.getAllProfesores();

        //Then
        assertNotNull(profesorOutputDtoList);
        assertEquals(1, profesorOutputDtoList.size());
    }

    @Test
    void updateProfesor() {
        //Given
        when(profesorRepository.findById(profesor.getIdProfesor())).thenReturn(Optional.ofNullable(profesor));
        when(profesorRepository.save(any(Profesor.class))).thenReturn(profesor);

        //When
        Profesor profesorActualizado = profesorRepository.findById(1).orElseThrow();
        profesorActualizado.setBranch("Profesor Actualizado");
        profesorRepository.save(profesorActualizado);

        //Then
        assertEquals("Profesor Actualizado", profesorActualizado.getBranch());
    }

    @Test
    void deleteProfesorById() {
        //Given
        when(profesorRepository.findById(profesor.getIdProfesor())).thenReturn(Optional.ofNullable(profesor));

        // When
        profesorServiceImpl.deleteProfesorById(profesor.getIdProfesor());

        //Then
        assertTrue(profesorRepository.findAll().isEmpty());
    }
}