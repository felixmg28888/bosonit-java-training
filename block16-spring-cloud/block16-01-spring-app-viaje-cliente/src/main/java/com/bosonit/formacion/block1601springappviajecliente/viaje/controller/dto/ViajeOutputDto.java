package com.bosonit.formacion.block1601springappviajecliente.viaje.controller.dto;

import com.bosonit.formacion.block1601springappviajecliente.cliente.domain.Cliente;
import com.bosonit.formacion.block1601springappviajecliente.viaje.domain.Viaje;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ViajeOutputDto {
    private Integer idViaje;
    private String origin;
    private String destination;
    private String departureDate;
    private String arrivalDate;
    private List<Cliente> passengers=new ArrayList<>();


    public ViajeOutputDto(Viaje viaje) {
        this.idViaje = viaje.getIdViaje();
        this.origin = viaje.getOrigin();
        this.destination = viaje.getDestination();
        this.departureDate = viaje.getDepartureDate();
        this.arrivalDate = viaje.getArrivalDate();
        this.passengers=viaje.getPassengers().stream().toList();
    }
}
