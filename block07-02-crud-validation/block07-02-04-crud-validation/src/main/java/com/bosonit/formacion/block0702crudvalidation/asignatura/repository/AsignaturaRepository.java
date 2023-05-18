package com.bosonit.formacion.block0702crudvalidation.asignatura.repository;

import com.bosonit.formacion.block0702crudvalidation.asignatura.domain.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsignaturaRepository extends JpaRepository<Asignatura, Integer> {
}
