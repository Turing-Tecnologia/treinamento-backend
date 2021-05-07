package com.turingtecnologia.albatroz.backendalbatroz.model.jpaRepositoy;

import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Clinica;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicaRepository extends JpaRepository<Clinica, Long> {

    Clinica findByCnpjClinica(String cnpjClinica);

}
