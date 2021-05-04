package com.turingtecnologia.albatroz.backendalbatroz.controller;

import com.turingtecnologia.albatroz.backendalbatroz.dto.ClienteDTO;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Cliente;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Consulta;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.ConsultaRealizada;
import com.turingtecnologia.albatroz.backendalbatroz.services.ReportService;

import com.turingtecnologia.albatroz.backendalbatroz.services.interfaces.AdministradorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/admin")
@AllArgsConstructor
public class AdministradorController {
    private final AdministradorService admService;

    private final ReportService reportService;

    @ApiOperation(value = "Gerar relatório completo.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Relatório gerado com sucesso."),
            @ApiResponse(code = 500, message = "Erro na geração do relatório.")
    })
    @GetMapping(value = "/gerarRelatorio/{formato}")
    public String geradorRelatorio( @PathVariable(value = "formato") String formato, 
                                    @RequestParam(required = false) Map<String, Object> parametros, 
                                    HttpServletResponse response) throws JRException, IOException, SQLException {
        return reportService.exportarRelatorio(formato, response, parametros);
    }

    @ApiOperation(value = "Gerar relatório por periódo.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Relatório gerado com sucesso."),
            @ApiResponse(code = 500, message = "Erro na geração do relatório.")
    })
    @GetMapping(value = "/gerarRelatorioData/{formato}")
    public String geradorRelatorioData( @PathVariable(value = "formato") String formato,
                                        @RequestParam Map<String, Object> parametros,
                                        HttpServletResponse response)
                                        throws JRException, IOException, SQLException, ParseException {
        return reportService.exportarRelatorioData(formato, response, parametros);
    }

    @ApiOperation(value = "Lista todos os clientes cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornado com sucesso os clientes."),
            @ApiResponse(code = 500, message = "Erro ao processar a listagem dos clientes.")
    })
    @GetMapping(value = "/clientes")
    public ResponseEntity<List<Cliente>> listaClientes() {
        return new ResponseEntity<>(admService.findAllClientes(), HttpStatus.OK);
    }

    @ApiOperation(value = "Apaga o cliente do banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente apagado com sucesso"),
            @ApiResponse(code = 500, message = "Erro ao deletar o cliente.")
    })
    @DeleteMapping(value = "/clientes")
    public ResponseEntity<Void> removeCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = new ModelMapper().map(clienteDTO, Cliente.class);
        admService.removeCliente(cliente);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualiza os dados do cliente.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Dados atualizados com sucesso."),
            @ApiResponse(code = 400, message = "Estar faltando dados para realizar a atualização."),
            @ApiResponse(code = 500, message = "Erro ao atualizar o cliente.")
    })
    @PutMapping(value = "/clientes")
    public ResponseEntity<Cliente> alteraCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = new ModelMapper().map(clienteDTO, Cliente.class);
        return new ResponseEntity<>(admService.alteraCliente(cliente), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualiza o estado da consulta para marcada, pelo id da consulta.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Atualização do estado processada com sucesso"),
            @ApiResponse(code = 400, message = "Falta o dado(id) para atualizar o estado."),
            @ApiResponse(code = 500, message = "Erro ao processar a atualização.")
    })
    @PutMapping(value = "/consultas/checkIn/{idConsulta}")
    public ResponseEntity<Consulta> checkIn(@PathVariable("idConsulta") Long idConsulta) {
        return new ResponseEntity<>(admService.marcaCheckIn(idConsulta), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualiza o estado da consulta para desmarcada, pelo id da consulta.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Atualização do estado processada com sucesso."),
            @ApiResponse(code = 400, message = "Falta o dado(id) para atualizar o estado."),
            @ApiResponse(code = 500, message = "Erro ao processar a atualização.")
    })
    @DeleteMapping(value = "/consultas/checkOut/{idConsulta}")
    public ResponseEntity<Void> checkOut(@PathVariable("idConsulta") Long idConsulta) {
        admService.marcaCheckOut(idConsulta);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Mostra todas as consultas cadastradas.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornado com sucesso a lista de consultas."),
            @ApiResponse(code = 500, message = "Erro ao processar a listagem de consulta.")
    })
    @GetMapping(value = "/consultas")
    public ResponseEntity<List<Consulta>> listaConsultas() {
        return new ResponseEntity<>(admService.findAllConsultas(), HttpStatus.OK);
    }

    @ApiOperation(value = "Mostra todas as consultas realizadas.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornado com sucesso a lista de consultas."),
            @ApiResponse(code = 500, message = "Erro ao processar a listagem de consulta.")
    })
    @GetMapping(value = "/consultasRealizadas")
    public ResponseEntity<List<ConsultaRealizada>> listaConsultasRealizadas() {
        return new ResponseEntity<>(admService.findAllConsultasRealizadas(), HttpStatus.OK);
    }

    @ApiOperation(value = "Mostra todas as consultas cadastradas pela data.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornado com sucesso a lista de consultas."),
            @ApiResponse(code = 500, message = "Erro ao processar a listagem de consulta.")
    })
    @GetMapping(value = "/consultas/{data}")
    public ResponseEntity<List<Cliente>> listaConsultasPorData(@PathVariable("data") String data) {
        return new ResponseEntity<>(admService.findConsultasPorData(data), HttpStatus.OK);
    }

    @ApiOperation(value = "Mostra todas as consultas cadastradas na data atual.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornado com sucesso a lista de consultas."),
            @ApiResponse(code = 500, message = "Erro ao processar a listagem de consulta.")
    })
    @GetMapping(value = "/consultas/consultasDoDia")
    public ResponseEntity<List<Cliente>> consultasDoDia() {
        return new ResponseEntity<>(admService.findConsultasDoDia(), HttpStatus.OK);
    }

    @ApiOperation(value = "Mostra todas as consultas realizadas em um dado período.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornado com sucesso a lista de consultas."),
            @ApiResponse(code = 500, message = "Erro ao processar a listagem de consultas.")
    })
    @GetMapping(value = "/consultasRealizadas/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<ConsultaRealizada>> consultaRealizadasPorPeriodo(@PathVariable String dataInicial, @PathVariable String dataFinal) {
        return new ResponseEntity<>(admService.findConsultasRealizadasPorPeriodo(dataInicial, dataFinal), HttpStatus.OK);
    }
}
