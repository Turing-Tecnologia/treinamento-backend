package com.turingtecnologia.albatroz.backendalbatroz.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.turingtecnologia.albatroz.backendalbatroz.dto.DentistaDTO;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Dentista;
import com.turingtecnologia.albatroz.backendalbatroz.services.interfaces.DentistaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/dentistas")
public class DentistaController {
    final DentistaService dentistaService;

    DentistaController(DentistaService service) {
        this.dentistaService = service;
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

        return new ResponseEntity<>(dentistaService.addDentista(dentista), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Realizar uma busca de dentista, pelo id do dentista.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Busca realizada com sucesso."),
            @ApiResponse(code = 400, message = "Falta o dado(id) para se realizar a busca."),
            @ApiResponse(code = 500, message = "Erro ao processar a busca.")
    })
    @GetMapping(value = "id/{idDentista}")
    public ResponseEntity<Dentista> busca(@PathVariable("idDentista") Long id) {
        return new ResponseEntity<>(dentistaService.findByIdDentista(id), HttpStatus.OK);
    }
    
    @ApiOperation(value = "Lista todos os dentistas cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornado com sucesso os dentistas."),
            @ApiResponse(code = 500, message = "Erro ao processar a listagem dos dentistas.")
    })
    @GetMapping(value = "/dentistas")
    public ResponseEntity<List<Dentista>> listaDentistas() {
        return new ResponseEntity<>(dentistaService.findAllDentistas(), HttpStatus.OK);
    }
    
    @ApiOperation(value = "Apaga o dentista do banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Dentista apagado com sucesso"),
            @ApiResponse(code = 500, message = "Erro ao deletar o dentista.")
    })
    
    @DeleteMapping(value = "remove/dentistas")
    public ResponseEntity<Void> removeDentista(@Valid @RequestBody DentistaDTO dentistaDTO) {
    	Dentista dentista = new ModelMapper().map(dentistaDTO, Dentista.class);
        dentistaService.removeDentista(dentista);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @ApiOperation(value = "Busca um dentista pelo CPF.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Busca realizada com sucesso."),
            @ApiResponse(code = 400, message = "Faltam dados para realizar a busca."),
            @ApiResponse(code = 500, message = "Erro ao processar a busca.")
    })
    @GetMapping(value = "cpf/{cpfDentista}")
    public ResponseEntity<Dentista> search(@PathVariable(value = "cpfDentista") String cpfDentista) {
        return new ResponseEntity<>(dentistaService.findByCpfDentista(cpfDentista), HttpStatus.OK);
    }
    
    @ApiOperation(value = "Atualiza os dados do cliente.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Dados atualizados com sucesso."),
            @ApiResponse(code = 400, message = "Estar faltando dados para realizar a atualização."),
            @ApiResponse(code = 500, message = "Erro ao atualizar o cliente.")
    })
    @PutMapping(value = "altera/dentistas")
    public ResponseEntity<Dentista> alteraDentista(@Valid @RequestBody DentistaDTO dentistaDTO) {
    	Dentista dentista = new ModelMapper().map(dentistaDTO, Dentista.class);
        return new ResponseEntity<>(dentistaService.alteraDentista(dentista), HttpStatus.CREATED);
    }
    
}
