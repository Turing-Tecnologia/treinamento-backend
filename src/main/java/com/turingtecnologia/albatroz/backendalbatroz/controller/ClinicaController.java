package com.turingtecnologia.albatroz.backendalbatroz.controller;

import javax.validation.Valid;

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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/clinicas")
@AllArgsConstructor
public class ClinicaController {
    final ClinicaService service;

    @ApiOperation(value = "Salva a clínica no Banco de Dados.")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Clínica salva com sucesso."),
        @ApiResponse(code = 400, message = "Parâmetros incorretos na requisição."),
        @ApiResponse(code = 500, message = "Erro ao salvar a clínica.")
    })
    @PostMapping
    public ResponseEntity<Clinica> save(@RequestBody @Valid ClinicaDTO dto) {
        Clinica clinica = new ModelMapper().map(dto, Clinica.class);
        clinica = service.addClinica(clinica);
        return new ResponseEntity<>(clinica, HttpStatus.CREATED);
    }

    @ApiOperation("Procura uma clínica pelo CNPJ.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Clínica encontrada."),
        @ApiResponse(code = 404, message = "CNPJ informado não existe."),
        @ApiResponse(code = 500, message = "Erro ao procurar clínica.")
    })
    @GetMapping("/{cnpjClinica}")
    public ResponseEntity<Clinica> get(@PathVariable String cnpjClinica) {
        Clinica clinica = service.findByCnpjClinica(cnpjClinica);
        return new ResponseEntity<>(clinica, HttpStatus.OK);
    }

    @ApiOperation(value = "Atualiza os dados de uma clínica.")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Cínica atualizada com sucesso."),
        @ApiResponse(code = 400, message = "Parâmetros incorretos na requisição."),
        @ApiResponse(code = 404, message = "CNPJ informado não existe."),
        @ApiResponse(code = 500, message = "Erro ao salvar a clínica.")
    })
    @PutMapping("/{cnpjClinica}")
    public ResponseEntity<Clinica> update(
        @PathVariable String cnpjClinica,
        @RequestBody @Valid ClinicaDTO dto
    ) {
        Clinica clinica = new ModelMapper().map(dto, Clinica.class);
        clinica = service.updateClinica(cnpjClinica, clinica);
        return new ResponseEntity<>(clinica, HttpStatus.OK);
    }

    @ApiOperation("Deleta uma clínica pelo CNPJ.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Clínica deletada com sucesso."),
        @ApiResponse(code = 404, message = "CNPJ informado não existe."),
        @ApiResponse(code = 500, message = "Erro ao procurar clínica.")
    })
    @DeleteMapping("/{cnpjClinica}")
    public ResponseEntity<Void> delete(@PathVariable String cnpjClinica) {
        service.deleteClinica(cnpjClinica);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
