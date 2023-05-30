package com.bosonit.formacion.block1601springappviajecliente.viaje.repository;

import com.bosonit.formacion.block1601springappviajecliente.viaje.domain.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViajeRepository extends JpaRepository <Viaje, Integer>  {
}
