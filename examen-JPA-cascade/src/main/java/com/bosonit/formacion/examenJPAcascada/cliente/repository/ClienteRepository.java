package com.bosonit.formacion.examenJPAcascada.cliente.repository;

import com.bosonit.formacion.examenJPAcascada.cliente.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Integer> {
}
