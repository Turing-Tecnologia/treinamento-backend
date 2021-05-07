package com.turingtecnologia.albatroz.backendalbatroz.model.jpaRepositoy;

import java.util.Optional;

import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Clinica;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicaRepository extends JpaRepository<Clinica, Long>{
    Optional<Clinica> findByCnpj(String cnpj);
}
