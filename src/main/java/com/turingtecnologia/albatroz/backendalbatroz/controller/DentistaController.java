package com.turingtecnologia.albatroz.backendalbatroz.controller;

import com.turingtecnologia.albatroz.backendalbatroz.dto.DentistaDTO;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Dentista;

import com.turingtecnologia.albatroz.backendalbatroz.services.interfaces.DentistaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/dentistas")
public class DentistaController {
    final DentistaService service;

    DentistaController(DentistaService service) {
        this.service = service;
    }

    @ApiOperation(value = "Salva o dentista no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Dentista salvo com sucesso."),
            @ApiResponse(code = 400, message = "Faltam dados para realizar a inserção."),
            @ApiResponse(code = 500, message = "Erro ao salvar o dentista.")
    })
    @PostMapping
    public ResponseEntity<Dentista> save(@Valid @RequestBody DentistaDTO dentistaDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Dentista dentista;
        dentista = modelMapper.map(dentistaDTO, Dentista.class);

        return new ResponseEntity<>(service.addDentista(dentista), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Busca um dentista pelo CPF.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Busca realizada com sucesso."),
            @ApiResponse(code = 400, message = "Faltam dados para realizar a busca."),
            @ApiResponse(code = 500, message = "Erro ao processar a busca.")
    })
    @GetMapping(value = "/{cpfDentista}")
    public ResponseEntity<Dentista> search(@PathVariable(value = "cpfDentista") String cpfDentista) {
        return new ResponseEntity<>(service.findByCpfDentista(cpfDentista), HttpStatus.OK);
    }
}
