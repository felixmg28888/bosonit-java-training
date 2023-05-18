package com.bosonit.formacion.block070203crudvalidation.student.repository;


import com.bosonit.formacion.block070203crudvalidation.persona.domain.Persona;
import com.bosonit.formacion.block070203crudvalidation.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByPersona(Persona persona);
}
