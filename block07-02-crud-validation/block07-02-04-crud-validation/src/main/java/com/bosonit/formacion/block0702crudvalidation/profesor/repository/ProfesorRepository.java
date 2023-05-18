package com.bosonit.formacion.block0702crudvalidation.profesor.repository;

import com.bosonit.formacion.block0702crudvalidation.persona.domain.Persona;
import com.bosonit.formacion.block0702crudvalidation.profesor.domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Integer> {
    Profesor findByPersona(Persona persona);
}
