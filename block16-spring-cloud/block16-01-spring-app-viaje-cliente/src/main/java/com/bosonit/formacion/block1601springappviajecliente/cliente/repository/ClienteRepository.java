package com.bosonit.formacion.block1601springappviajecliente.cliente.repository;

import com.bosonit.formacion.block1601springappviajecliente.cliente.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository <Cliente, Integer>  {
}
