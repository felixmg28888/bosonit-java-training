package com.bosonit.formacion.examenJPAcascada.cabeceraFra.repository;

import com.bosonit.formacion.examenJPAcascada.cabeceraFra.domain.CabeceraFra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CabeceraFraRepository extends  JpaRepository<CabeceraFra, Integer> {
}
