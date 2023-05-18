package com.bosonit.formacion.block13testingcrud.profesor.controller;

import com.bosonit.formacion.block13testingcrud.profesor.application.ProfesorServiceImpl;
import com.bosonit.formacion.block13testingcrud.profesor.controller.dto.ProfesorInputDto;
import com.bosonit.formacion.block13testingcrud.profesor.controller.dto.ProfesorOutputDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProfesorControllerTest {

    @Mock
    ProfesorServiceImpl profesorServiceImpl;
    @InjectMocks
    ProfesorController profesorController;
    private ProfesorInputDto profesorInputDto;
    private ProfesorOutputDto profesorOutputDto;



    @Test
    void addProfesor() {
        //Given -
        when(profesorServiceImpl.addProfesor(profesorInputDto)).thenReturn(profesorOutputDto);

        //When -
        ResponseEntity<ProfesorOutputDto> profesorOutputDtoResponseEntity = profesorController.addProfesor(profesorInputDto);

        //Then -
        assertEquals(HttpStatus.CREATED, profesorOutputDtoResponseEntity.getStatusCode());
    }

    @Test
    void assignStudentToProfesor() {
        //Given -
        when(profesorServiceImpl.assignStudentToProfesor(1,1)).thenReturn("Proceso con éxito");

        //When -
        ResponseEntity<String> profesorOutputDtoResponseEntity = profesorController.assignStudentToProfesor(1,1);

        //Then -
        assertEquals(HttpStatus.OK, profesorOutputDtoResponseEntity.getStatusCode());
    }

    @Test
    void getProfesorById() {
        //Given -
        when(profesorServiceImpl.getProfesorById(1)).thenReturn(profesorOutputDto);

        //When -
        ResponseEntity<ProfesorOutputDto> profesorOutputDtoResponseEntity = profesorController.getProfesorById(1);

        //Then -
        assertEquals(HttpStatus.OK, profesorOutputDtoResponseEntity.getStatusCode());


    }

    @Test
    void getAllProfesores() {
        //Given -
        List<ProfesorOutputDto> profesorOutputDtoList = new ArrayList<>();
        profesorOutputDtoList.add(profesorOutputDto);
        when(profesorServiceImpl.getAllProfesores()).thenReturn(profesorOutputDtoList);

        //When -
        ResponseEntity<?> profesorOutputDtoResponseEntity =  profesorController.getAllProfesores();

        //Then -
        assertEquals(HttpStatus.OK, profesorOutputDtoResponseEntity.getStatusCode());

    }

    @Test
    void updateProfesor() {
        //Given -
        //profesorInputDto.setBranch("Actualizado");
        when(profesorServiceImpl.updateProfesor(1,profesorInputDto)).thenReturn(profesorOutputDto);

        //When -
        ResponseEntity<ProfesorOutputDto> profesorOutputDtoResponseEntity =  profesorController.updateProfesor(1,profesorInputDto);
        //Then -
        assertEquals(HttpStatus.OK, profesorOutputDtoResponseEntity.getStatusCode());
    }

    @Test
    void deleteProfesorById() {
        //Given -
        when(profesorServiceImpl.deleteProfesorById(1)).thenReturn("Profesor borrado con éxito");

        //When -
        ResponseEntity<String> personaOutputDtoResponseEntity = profesorController.deleteProfesorById(1);

        //Then -
        assertEquals(HttpStatus.OK, personaOutputDtoResponseEntity.getStatusCode());
        assertEquals("Profesor borrado con éxito", personaOutputDtoResponseEntity.getBody());



    }
}