package com.bosonit.formacion.block0702crudvalidation.asignatura.controller.dto;

import com.bosonit.formacion.block0702crudvalidation.asignatura.domain.Asignatura;
import lombok.Getter;

import java.util.Date;

@Getter
public class AsignaturaOutputDto {

    private Integer idAsignatura;
    private String nombreAsignatura;
    private String comments;
    private String branch;
    private Date initial_date;
    private Date finish_date;

    public AsignaturaOutputDto(Asignatura asignatura) {
        this.idAsignatura = asignatura.getIdAsignatura();
        this.nombreAsignatura = asignatura.getNombreAsignatura();
        this.comments = asignatura.getComments();
        this.branch = asignatura.getBranch();
        this.initial_date = asignatura.getInitial_date();
        this.finish_date = asignatura.getFinish_date();
    }
}
