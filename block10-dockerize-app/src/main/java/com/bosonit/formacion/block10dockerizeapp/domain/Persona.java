package com.bosonit.formacion.block10dockerizeapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
public class Persona {
    @Id
    @GeneratedValue
    Integer id;
    String name;
    Integer age;
    String city;
}
