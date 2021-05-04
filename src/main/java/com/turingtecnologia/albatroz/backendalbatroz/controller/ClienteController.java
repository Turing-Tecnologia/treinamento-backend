package com.turingtecnologia.albatroz.backendalbatroz.controller;

import com.turingtecnologia.albatroz.backendalbatroz.dto.ClienteDTO;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Cliente;

import com.turingtecnologia.albatroz.backendalbatroz.services.interfaces.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {
    final ClienteService service;

    ClienteController(ClienteService service) {
        this.service = service;
    }

    @ApiOperation(value = "Salva o cliente no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente salvo com sucesso."),
            @ApiResponse(code = 400, message = "Faltam dados para realizar a inserção."),
            @ApiResponse(code = 500, message = "Erro ao salvar o cliente.")
    })
    @PostMapping
    public ResponseEntity<Cliente> save(@Valid @RequestBody ClienteDTO clienteDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Cliente cliente;
        cliente = modelMapper.map(clienteDTO, Cliente.class);

        return new ResponseEntity<>(service.addCliente(cliente), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Busca um cliente pelo CPF.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Busca realizada com sucesso."),
            @ApiResponse(code = 400, message = "Faltam dados para realizar a busca."),
            @ApiResponse(code = 500, message = "Erro ao processar a busca.")
    })
    @GetMapping(value = "/{cpfCliente}")
    public ResponseEntity<Cliente> search(@PathVariable(value = "cpfCliente") String cpfCliente) {
        return new ResponseEntity<>(service.findByCpfCliente(cpfCliente), HttpStatus.OK);
    }
}
