package com.bosonit.formacion.block070203crudvalidation.profesor.application;

import com.bosonit.formacion.block070203crudvalidation.profesor.controller.dto.ProfesorInputDto;
import com.bosonit.formacion.block070203crudvalidation.profesor.controller.dto.ProfesorOutputDto;

import java.util.List;

public interface ProfesorService {

    ProfesorOutputDto addProfesor(ProfesorInputDto profesorInputDto);

    String assignStudentToProfesor(Integer idProfesor, Integer idStudent);

    ProfesorOutputDto getProfesorById(Integer id);

    List<ProfesorOutputDto> getAllProfesores();

    ProfesorOutputDto updateProfesor(Integer id, ProfesorInputDto profesorInputDto);

    String deleteProfesorById(Integer id);
}
