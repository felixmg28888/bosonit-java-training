package com.bosonit.formacion.block1201criteriabuilder.persona.repository;

import com.bosonit.formacion.block1201criteriabuilder.persona.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    Optional<List<Persona>> findByName(String nombre);
}
