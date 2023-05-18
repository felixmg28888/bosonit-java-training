package com.bosonit.formacion.block1201criteriabuilder.profesor.repository;

import com.bosonit.formacion.block1201criteriabuilder.persona.domain.Persona;
import com.bosonit.formacion.block1201criteriabuilder.profesor.domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Integer> {
    Profesor findByPersona(Persona persona);
}
