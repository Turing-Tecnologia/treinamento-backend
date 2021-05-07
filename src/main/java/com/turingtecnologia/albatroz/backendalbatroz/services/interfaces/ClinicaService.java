package com.turingtecnologia.albatroz.backendalbatroz.services.interfaces;

import com.turingtecnologia.albatroz.backendalbatroz.dto.ClinicaDTO;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Clinica;

public interface ClinicaService {
    Clinica salvar(ClinicaDTO dto);

    Clinica editar(ClinicaDTO dto);

    void excluir(String CNPJ);

    Clinica getClinica(String CNPJ);
}
