package com.turingtecnologia.albatroz.backendalbatroz.services.implementations;

import com.turingtecnologia.albatroz.backendalbatroz.dto.ClinicaDTO;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Clinica;
import com.turingtecnologia.albatroz.backendalbatroz.model.jpaRepositoy.ClinicaRepository;
import com.turingtecnologia.albatroz.backendalbatroz.services.interfaces.ClinicaService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClinicaServiceImplementation implements ClinicaService{

    private final ClinicaRepository clinicaRepo;

    @Override
    public Clinica editar(ClinicaDTO dto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void excluir(String CNPJ) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void getClinica(String CNPJ) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Clinica salvar(ClinicaDTO dto) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
