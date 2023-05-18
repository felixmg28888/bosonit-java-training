package com.bosonit.formacion.block13testingcrud.asignatura.repository;

import com.bosonit.formacion.block13testingcrud.asignatura.domain.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsignaturaRepository extends JpaRepository<Asignatura, Integer> {
}
