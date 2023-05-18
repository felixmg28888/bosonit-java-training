package com.bosonit.formacion.block070201crudvalidation.persona.controller.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class PersonaInputDto {
    private String idPersona;
    private String usuario;
    private String password;
    private String name;
    private String surname;
    private String companyEmail;
    private String personalEmail;
    private String city;
    private Boolean active;
    //private Date createdDate;
    private String imagenUrl;
    private Date terminationDate;

}
