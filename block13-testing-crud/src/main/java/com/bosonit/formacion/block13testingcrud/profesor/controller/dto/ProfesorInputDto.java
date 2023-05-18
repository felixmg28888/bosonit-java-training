package com.bosonit.formacion.block13testingcrud.profesor.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfesorInputDto {
    private Integer idPersona;
    private String comments;
    private String branch;
}