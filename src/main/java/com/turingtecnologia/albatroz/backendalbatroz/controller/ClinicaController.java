package com.turingtecnologia.albatroz.backendalbatroz.controller;

import javax.validation.Valid;

import com.turingtecnologia.albatroz.backendalbatroz.dto.ClinicaDTO;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Clinica;
import com.turingtecnologia.albatroz.backendalbatroz.services.interfaces.ClinicaService;

import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/clinicas")
public class ClinicaController {
    
    @Autowired
    private ClinicaService service;

    @GetMapping("/{cnpj}")
    public ResponseEntity<Clinica> getClinica(@PathVariable("cnpj") @CNPJ String cnpj){
        return new ResponseEntity<>(service.getClinica(cnpj), HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<Clinica> adicionarClinica(@RequestBody @Valid ClinicaDTO dto){
        return new ResponseEntity<>(service.salvar(dto),HttpStatus.CREATED);
    }

    @PutMapping("/{cnpj}")
    public ResponseEntity<Clinica> editarClinica(@PathVariable("cnpj") @CNPJ String cnpj, @RequestBody @Valid ClinicaDTO dto){
        return new ResponseEntity<>(service.editar(cnpj,dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{cnpj}")
    public ResponseEntity<Void> removerClinica(@PathVariable("cnpj") @CNPJ String cnpj){
        service.excluir(cnpj);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
