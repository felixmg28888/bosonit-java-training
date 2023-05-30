package com.bosonit.formacion.block1601springappviajecliente.viaje.controller.dto;

import lombok.Getter;


@Getter
public class ViajeInputDto {
    private String origin;
    private String destination;
    private String departureDate;
    private String arrivalDate;
}
