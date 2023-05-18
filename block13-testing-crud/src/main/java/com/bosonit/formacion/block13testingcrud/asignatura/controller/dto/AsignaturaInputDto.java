package com.bosonit.formacion.block13testingcrud.asignatura.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AsignaturaInputDto {
    private String nombreAsignatura;
    private String comments;
    private String branch;
    private Date finishDate;
}