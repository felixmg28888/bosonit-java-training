package com.bosonit.formacion.examenJPAcascada.lineasFra.repository;

import com.bosonit.formacion.examenJPAcascada.lineasFra.domain.LineaFra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineaFraRepository extends JpaRepository<LineaFra,Integer> {
}
