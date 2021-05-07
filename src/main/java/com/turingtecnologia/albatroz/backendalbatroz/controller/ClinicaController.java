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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/admin/clinicas")
public class ClinicaController {
    
    @Autowired
    private ClinicaService service;

    @ApiOperation(value = "Realizar uma busca de clinica, pelo cnpj da clinica.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Busca realizada com sucesso."),
            @ApiResponse(code = 400, message = "Falta o dado(cnpj) para se realizar a busca."),
            @ApiResponse(code = 500, message = "Erro ao processar a busca.")
    })
    @GetMapping("/{cnpj}")
    public ResponseEntity<Clinica> getClinica(@PathVariable("cnpj") @CNPJ String cnpj){
        return new ResponseEntity<>(service.getClinica(cnpj), HttpStatus.CREATED);
    }

    /*----------------*/

    @ApiOperation(value = "Cadastro de clinica.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Clinica cadastrada com sucesso."),
            @ApiResponse(code = 400, message = "Falta dados para se realizar o cadastro."),
            @ApiResponse(code = 500, message = "Erro ao processar o cadastro.")
    })
    @PostMapping
    public ResponseEntity<Clinica> adicionarClinica(@RequestBody @Valid ClinicaDTO dto){
        return new ResponseEntity<>(service.salvar(dto),HttpStatus.CREATED);
    }

    /*----------------*/
    @ApiOperation(value = "Edita a clinica do banco de dados, pelo cnpj e objeto da clinica.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Clinica edita com sucesso."),
            @ApiResponse(code = 400, message = "Falta o dados para editar a clinica."),
            @ApiResponse(code = 500, message = "Erro ao processar a edição!.")
    })
    @PutMapping("/{cnpj}")
    public ResponseEntity<Clinica> editarClinica(@PathVariable("cnpj") @CNPJ String cnpj, @RequestBody @Valid ClinicaDTO dto){
        return new ResponseEntity<>(service.editar(cnpj,dto), HttpStatus.CREATED);
    }

    /*----------------*/

    @ApiOperation(value = "Deleta a clinica do banco de dados, pelo cnpj da clinica.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Clinica deletada com sucesso."),
            @ApiResponse(code = 400, message = "Falta o dado(cnpj) para deletar a clinica."),
            @ApiResponse(code = 500, message = "Erro ao processar o delete.")
    })
    @DeleteMapping("/{cnpj}")
    public ResponseEntity<Void> removerClinica(@PathVariable("cnpj") @CNPJ String cnpj){
        service.excluir(cnpj);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
