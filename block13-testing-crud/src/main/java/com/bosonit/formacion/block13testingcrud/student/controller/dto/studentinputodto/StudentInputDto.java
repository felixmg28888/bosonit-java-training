package com.bosonit.formacion.block13testingcrud.student.controller.dto.studentinputodto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentInputDto {
    private Integer idPersona;
    private Integer numHourWeek;
    private String comments;
    private String branch;
}