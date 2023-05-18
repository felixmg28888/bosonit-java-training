package com.bosonit.formacion.block070201crudvalidation.persona.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
@Table
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_persona", unique = true)
    private Integer idPersona;

    @Column(nullable = false, length = 10, unique = true)
    private String usuario;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    private String surname;

    //Añado unique a varios campos no podrá haber dos personas con el mismo email tanto personal como de empresa además de la foto.
    @Column(name = "comany_email", nullable = false, unique = true)
    private String companyEmail;

    @Column(name = "persona_email", nullable = false, unique = true)
    private String personalEmail;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private Boolean active;

    @CreationTimestamp//Introduce fecha de creación de entidad, nunca será nula.
    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "imagen_url", unique = true)
    private String imagenUrl;

    @Column(name = "termination_date")
    private Date terminationDate;
}