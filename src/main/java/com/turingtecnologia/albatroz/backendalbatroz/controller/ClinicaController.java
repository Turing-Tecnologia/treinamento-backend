package com.turingtecnologia.albatroz.backendalbatroz.controller;

import com.turingtecnologia.albatroz.backendalbatroz.dto.ClinicaDTO;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Clinica;
import com.turingtecnologia.albatroz.backendalbatroz.services.interfaces.ClinicaService;

import org.modelmapper.ModelMapper;
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

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/clinicas")
@AllArgsConstructor
public class ClinicaController {
    final ClinicaService service;

    @PostMapping
    public ResponseEntity<Clinica> save(@RequestBody ClinicaDTO dto) {
        Clinica clinica = new ModelMapper().map(dto, Clinica.class);
        clinica = service.addClinica(clinica);
        return new ResponseEntity<>(clinica, HttpStatus.CREATED);
    }

    @GetMapping("/{cnpjClinica}")
    public ResponseEntity<Clinica> get(@PathVariable String cnpjClinica) {
        Clinica clinica = service.findByCnpjClinica(cnpjClinica);
        return new ResponseEntity<>(clinica, HttpStatus.OK);
    }

    @PutMapping("/{cnpjClinica}")
    public ResponseEntity<Clinica> update(
        @PathVariable String cnpjClinica,
        @RequestBody ClinicaDTO dto
    ) {
        Clinica clinica = new ModelMapper().map(dto, Clinica.class);
        clinica = service.updateClinica(cnpjClinica, clinica);
        return new ResponseEntity<>(clinica, HttpStatus.OK);
    }

    @DeleteMapping("/{cnpjClinica}")
    public ResponseEntity<Void> delete(@PathVariable String cnpjClinica) {
        service.deleteClinica(cnpjClinica);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
