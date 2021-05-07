package com.turingtecnologia.albatroz.backendalbatroz.controller;

import javax.validation.Valid;

import com.sendgrid.Response;
import com.turingtecnologia.albatroz.backendalbatroz.dto.ClinicaDTO;
import com.turingtecnologia.albatroz.backendalbatroz.dto.InfoClinicaDTO;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Clinica;
import com.turingtecnologia.albatroz.backendalbatroz.services.interfaces.ClinicaService;
import com.turingtecnologia.albatroz.backendalbatroz.services.interfaces.ConsultaService;

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

    @Autowired
    private ConsultaService consultaService;

    @GetMapping("/{cnpj}")
    public ResponseEntity<InfoClinicaDTO> getClinica(@PathVariable("cnpj") String cnpj){
        return new ResponseEntity<>(converter(service.getClinica(cnpj)), HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<Clinica> adicionarClinica(@RequestBody @Valid ClinicaDTO dto){
        return new ResponseEntity<>(service.salvar(dto),HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Clinica> editarClinica(@RequestBody @Valid ClinicaDTO dto){
        return new ResponseEntity<>(service.editar(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{cnpj}")
    public ResponseEntity<Void> removerClinica(@PathVariable("cnpj") @CNPJ String cnpj){
        service.excluir(cnpj);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public InfoClinicaDTO converter(Clinica clinica){
        return InfoClinicaDTO.builder()
                    .nomeClinica(clinica.getNomeClinica())
                    .telefone(clinica.getTelefone())
                    .email(clinica.getEmail())
                    .cnpj(clinica.getCnpj())
                    .consultas(consultaService.converter(clinica.getConsultasMarcadas()))
                    .build();
    }
}
