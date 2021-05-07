package com.turingtecnologia.albatroz.backendalbatroz.controller;

import javax.validation.Valid;

import com.turingtecnologia.albatroz.backendalbatroz.dto.ConsultaDTO;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Consulta;
import com.turingtecnologia.albatroz.backendalbatroz.services.interfaces.ConsultaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/consultas")
@AllArgsConstructor
public class ConsultaController {

    private final ConsultaService consultaService;

    @ApiOperation(value = "Cadastro de consulta.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Consulta cadastrada com sucesso."),
            @ApiResponse(code = 400, message = "Falta dados para se realizar o cadastro."),
            @ApiResponse(code = 500, message = "Erro ao processar o cadastro.")
    })
    @PostMapping
    public ResponseEntity<Consulta> salva(@Valid @RequestBody ConsultaDTO consultaDTO) {
        Consulta consulta = new ModelMapper().map(consultaDTO, Consulta.class);
        
        return new ResponseEntity<>(consultaService.addConsulta(consulta), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Realizar uma busca de consulta, pelo id da consulta.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Busca realizada com sucesso."),
            @ApiResponse(code = 400, message = "Falta o dado(id) para se realizar a busca."),
            @ApiResponse(code = 500, message = "Erro ao processar a busca.")
    })
    @GetMapping(value = "/{idConsulta}")
    public ResponseEntity<Consulta> busca(@PathVariable("idConsulta") Long id) {
        return new ResponseEntity<>(consultaService.findByIdConsulta(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Deleta a consulta do banco de dados, pelo id da consulta.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Consulta deletada com sucesso."),
            @ApiResponse(code = 400, message = "Falta o dado(id) para deletar a consulta."),
            @ApiResponse(code = 500, message = "Erro ao processar o delete.")
    })
    @DeleteMapping(value = "/{idConsulta}")
    public ResponseEntity<Void> desmarca(@PathVariable("idConsulta") Long id) {
        consultaService.remove(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualiza a consulta no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Consulta atualizada com sucesso."),
            @ApiResponse(code = 400, message = "Falta dados para realizar a atualização."),
            @ApiResponse(code = 500, message = "Erro ao processar a atualização.")
    })
    @PutMapping
    public ResponseEntity<Consulta> altera(@Valid @RequestBody ConsultaDTO consultaDTO) {
        Consulta consulta = new ModelMapper().map(consultaDTO, Consulta.class);
        return new ResponseEntity<>(consultaService.alteraConsulta(consulta), HttpStatus.CREATED);
    }

}
