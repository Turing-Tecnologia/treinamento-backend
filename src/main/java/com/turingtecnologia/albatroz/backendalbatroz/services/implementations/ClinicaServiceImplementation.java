package com.turingtecnologia.albatroz.backendalbatroz.services.implementations;

import com.turingtecnologia.albatroz.backendalbatroz.dto.ClinicaDTO;
import com.turingtecnologia.albatroz.backendalbatroz.exceptions.error.ResourceNotFoundException;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Clinica;
import com.turingtecnologia.albatroz.backendalbatroz.model.jpaRepositoy.ClinicaRepository;
import com.turingtecnologia.albatroz.backendalbatroz.services.interfaces.ClinicaService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClinicaServiceImplementation implements ClinicaService{

    private final ClinicaRepository clinicaRepo;

    @Override
    @Transactional
    public Clinica editar(ClinicaDTO dto) {
        if(clinicaRepo.findByCnpj(dto.getCnpj()) != null)
        {
            return clinicaRepo.save(converterDTO(dto));
        }
        else{
            throw new ResourceNotFoundException("Esta clinica não existe!");
        }
    }

    @Override
    @Transactional
    public void excluir(String cnpj) {
        cnpj = tratarCNPJ(cnpj);
        clinicaRepo.delete(clinicaRepo.findByCnpj(cnpj));
    }

    @Override
    @Transactional
    public Clinica getClinica(String cnpj) {
        return clinicaRepo.findByCnpj(cnpj);
    }

    @Override
    @Transactional
    public Clinica salvar(ClinicaDTO dto) {
        dto.setCnpj(tratarCNPJ(dto.getCnpj()));
        return clinicaRepo.save(converterDTO(dto));
    }

    private Clinica converterDTO(ClinicaDTO dto){
        Clinica clinica = new ModelMapper().map(dto, Clinica.class);
        return clinica;
    }

    private String tratarCNPJ(String cnpj){
        return cnpj.replaceAll("[./-]", "");
    }
    
}
