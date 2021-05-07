package com.turingtecnologia.albatroz.backendalbatroz.controller;

import com.turingtecnologia.albatroz.backendalbatroz.dto.ClinicaDTO;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Clinica;
import com.turingtecnologia.albatroz.backendalbatroz.model.jpaRepositoy.ClinicaRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/clinicas")
public class ClinicaController {
    
    @Autowired
    ClinicaRepository clinicaRepo;

    @PostMapping
    public ResponseEntity<Clinica> adicionarClinica(@RequestBody ClinicaDTO clinicaDTO){
        Clinica clinica = new ModelMapper().map(clinicaDTO, Clinica.class);
        return new ResponseEntity<>(clinicaRepo.save(clinica),HttpStatus.CREATED);
    }


}
