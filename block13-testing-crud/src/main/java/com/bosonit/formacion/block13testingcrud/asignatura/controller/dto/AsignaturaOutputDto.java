package com.bosonit.formacion.block13testingcrud.asignatura.controller.dto;

import com.bosonit.formacion.block13testingcrud.asignatura.domain.Asignatura;
import lombok.Getter;

import java.util.Date;

@Getter
public class AsignaturaOutputDto {

    private Integer idAsignatura;
    private String nombreAsignatura;
    private String comments;
    private String branch;
    private Date initialDate;
    private Date finishDate;

    public AsignaturaOutputDto(Asignatura asignatura) {
        this.idAsignatura = asignatura.getIdAsignatura();
        this.nombreAsignatura = asignatura.getNombreAsignatura();
        this.comments = asignatura.getComments();
        this.branch = asignatura.getBranch();
        this.initialDate = asignatura.getInitialDate();
        this.finishDate = asignatura.getFinishDate();
    }
}
