package com.turingtecnologia.albatroz.backendalbatroz.services.implementations;

import com.turingtecnologia.albatroz.backendalbatroz.exceptions.error.ResourceNotFoundException;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Clinica;
import com.turingtecnologia.albatroz.backendalbatroz.model.jpaRepositoy.ClinicaRepository;
import com.turingtecnologia.albatroz.backendalbatroz.services.interfaces.ClinicaService;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClinicaServiceImplementation implements ClinicaService {
    final ClinicaRepository repository;

    @Override
    public Clinica addClinica(Clinica clinica) {
        return repository.save(clinica);
    }

    @Override
    public Clinica findByCnpjClinica(String cnpj) {
        verificaSeClinicaExiste(cnpj);
        return repository.findByCnpjClinica(cnpj);
    }

    @Override
    public Clinica updateClinica(String cnpjClinica, Clinica clinica) {
        Clinica existente = findByCnpjClinica(cnpjClinica);
        existente.setCnpjClinica(clinica.getCnpjClinica());
        existente.setNomeClinica(clinica.getNomeClinica());
        existente.setEnderecoClinica(clinica.getEnderecoClinica());
        existente.setEmailClinica(clinica.getEmailClinica());
        return repository.save(existente);
    }

    @Override
    public void deleteClinica(String cnpjClinica) {
        Clinica clinica = findByCnpjClinica(cnpjClinica);
        repository.delete(clinica);
    }

    private void verificaSeClinicaExiste(String cnpj) {
        if (repository.findByCnpjClinica(cnpj) == null)
            throw new ResourceNotFoundException("Não existe clínica com este CNPJ.");
    }
}
