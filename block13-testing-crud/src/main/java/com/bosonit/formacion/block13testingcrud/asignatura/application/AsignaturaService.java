package com.bosonit.formacion.block13testingcrud.asignatura.application;

import com.bosonit.formacion.block13testingcrud.asignatura.controller.dto.AsignaturaInputDto;
import com.bosonit.formacion.block13testingcrud.asignatura.controller.dto.AsignaturaOutputDto;

import java.util.List;

public interface AsignaturaService {
    AsignaturaOutputDto addAsignatura(AsignaturaInputDto asignaturaInputDto);

    AsignaturaOutputDto getAsignaturaById(Integer id);

    List<AsignaturaOutputDto> getAllAsignaturas();

    AsignaturaOutputDto updateAsignatura(Integer id, AsignaturaInputDto asignaturaInputDto);

    String deleteAsignaturaById(Integer id);
}
