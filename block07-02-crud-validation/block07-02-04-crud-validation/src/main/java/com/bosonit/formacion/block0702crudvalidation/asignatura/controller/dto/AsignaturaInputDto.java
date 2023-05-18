package com.bosonit.formacion.block0702crudvalidation.asignatura.controller.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class AsignaturaInputDto {
    private String nombreAsignatura;
    private String comments;
    private String branch;
    private Date finish_date;
}