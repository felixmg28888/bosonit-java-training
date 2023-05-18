package com.bosonit.formacion.block13testingcrud.asignatura.domain;

import com.bosonit.formacion.block13testingcrud.student.domain.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table
public class Asignatura {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_Asignatura", unique = true)
    private Integer idAsignatura;
    @Column(unique = true)
    private String nombreAsignatura;
    private String comments;
    private String branch;
    @Column(nullable = false)
    private Date initialDate = new Date();
    private Date finishDate;


    //------------------------Relaciones------------------------
    @ManyToMany(mappedBy = "asignaturas")//, cascade=CascadeType.ALL)
    private List<Student> students = new ArrayList<>();
    // ----------------------------------------------------------


}

