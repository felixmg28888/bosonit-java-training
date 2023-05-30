package com.bosonit.formacion.block1601springappviajecliente.viaje.application;

import com.bosonit.formacion.block1601springappviajecliente.cliente.controller.dto.ClienteInputDto;
import com.bosonit.formacion.block1601springappviajecliente.cliente.controller.dto.ClienteOutputDto;
import com.bosonit.formacion.block1601springappviajecliente.viaje.controller.dto.ViajeInputDto;
import com.bosonit.formacion.block1601springappviajecliente.viaje.controller.dto.ViajeOutputDto;

import java.util.List;

public interface ViajeService {
    ViajeOutputDto addViaje(ViajeInputDto viajeInputDto);

    ViajeOutputDto getViajeById(Integer id);


    List<ViajeOutputDto> getAllViajes();

    ViajeOutputDto updateViaje(Integer id, ViajeInputDto viajeInputDto);

    String deleteViaje(Integer id);

    String addPassenger(Integer idViaje, Integer idCliente);

    Integer countPassenger(Integer idViaje);

    String updateStatus(Integer idViaje, String status);

    String verifyViaje(Integer idViaje);
}
