package com.turingtecnologia.albatroz.backendalbatroz.services.interfaces;

import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Clinica;

public interface ClinicaService {
    Clinica addClinica(Clinica clinica);
    Clinica findByCnpjClinica(String cnpj);
    Clinica updateClinica(String cnpjClinica, Clinica clinica);
    void deleteClinica(String cnpjClinica);
}
