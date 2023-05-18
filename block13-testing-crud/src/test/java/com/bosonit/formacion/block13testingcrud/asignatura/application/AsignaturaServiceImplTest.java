package com.bosonit.formacion.block13testingcrud.asignatura.application;

import com.bosonit.formacion.block13testingcrud.asignatura.controller.dto.AsignaturaInputDto;
import com.bosonit.formacion.block13testingcrud.asignatura.controller.dto.AsignaturaOutputDto;
import com.bosonit.formacion.block13testingcrud.asignatura.domain.Asignatura;
import com.bosonit.formacion.block13testingcrud.asignatura.repository.AsignaturaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AsignaturaServiceImplTest {

    @Mock
    private AsignaturaRepository asignaturaRepository;
    @InjectMocks
    private AsignaturaServiceImpl asignaturaServiceImpl;

    private AsignaturaInputDto asignaturaInputDto;
    private Asignatura asignatura;
    private AsignaturaOutputDto asignaturaOutputDto;

    @BeforeEach
    void setUp() {
        asignaturaInputDto = new AsignaturaInputDto();
        asignaturaInputDto.setNombreAsignatura("Asignatura1");
        asignaturaInputDto.setComments("Comentario Asignatura1");
        asignaturaInputDto.setBranch("Branch Asignatura1");

        asignatura = new Asignatura();
        asignatura.setIdAsignatura(1);
        asignatura.setNombreAsignatura(asignaturaInputDto.getNombreAsignatura());
        asignatura.setComments(asignaturaInputDto.getComments());
        asignatura.setBranch(asignaturaInputDto.getBranch());
        asignatura.setInitialDate(new Date());


    }

    @Test
    void addAsignatura() {
        //Given
        when(asignaturaRepository.save(any(Asignatura.class))).thenReturn(asignatura);

        //When
        asignaturaOutputDto = asignaturaServiceImpl.addAsignatura(asignaturaInputDto);

        //Then
        assertEquals(asignaturaOutputDto.getNombreAsignatura(), asignatura.getNombreAsignatura());
        assertEquals(asignaturaOutputDto.getNombreAsignatura(), asignaturaInputDto.getNombreAsignatura());
    }

    @Test
    void getAsignaturaById() {
        //Given
        when(asignaturaRepository.findById(asignatura.getIdAsignatura())).thenReturn(Optional.ofNullable(asignatura));

        //When
        asignaturaOutputDto = asignaturaServiceImpl.getAsignaturaById(1);

        //Then
        assertEquals(asignaturaOutputDto.getNombreAsignatura(), asignatura.getNombreAsignatura());
        assertEquals(asignaturaOutputDto.getNombreAsignatura(), asignaturaInputDto.getNombreAsignatura());
    }

    @Test
    void getAllAsignaturas() {
        //Given
        when(asignaturaRepository.findAll()).thenReturn(List.of(asignatura));

        //When
        List<AsignaturaOutputDto> asignaturaOutputDtoList = asignaturaServiceImpl.getAllAsignaturas();

        //Then
        assertNotNull(asignaturaOutputDtoList);
        assertEquals(1, asignaturaOutputDtoList.size());
    }

    @Test
    void updateAsignatura() {
        //Given
        when(asignaturaRepository.findById(asignatura.getIdAsignatura())).thenReturn(Optional.ofNullable(asignatura));
        when(asignaturaRepository.save(any(Asignatura.class))).thenReturn(asignatura);

        //When
        Asignatura asignaturaActualizar = asignaturaRepository.findById(1).orElseThrow();
        asignaturaActualizar.setNombreAsignatura("Asignatura Actualizada");
        asignaturaRepository.save(asignaturaActualizar);

        //Then
        assertEquals("Asignatura Actualizada", asignaturaActualizar.getNombreAsignatura());
    }

    @Test
    void deleteAsignaturaById() {
        //Given
        when(asignaturaRepository.findById(asignatura.getIdAsignatura())).thenReturn(Optional.ofNullable(asignatura));

        // When
        asignaturaServiceImpl.deleteAsignaturaById(asignatura.getIdAsignatura());

        //Then
        assertTrue(asignaturaRepository.findAll().isEmpty());
    }
}