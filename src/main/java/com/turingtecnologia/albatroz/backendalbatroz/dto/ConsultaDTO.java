package com.turingtecnologia.albatroz.backendalbatroz.dto;

import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Cliente;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ConsultaDTO {
    @NotBlank(message ="Check In " + "{not.blank}")
    private Date checkInConsulta;

    @NotBlank(message = "Check Out " + "{not.blank}")
    private Date checkOutConsulta;

    @NotBlank(message = "Data da consulta " + "{not.blank}")
    private Calendar dataConsulta;

    @NotBlank(message = "Especialidade da consulta " + "{not.blank}")
    private String especialidadeConsulta;

    @NotBlank(message = "Numero da Ficha da consulta " + "{not.blank}")
    private int numeroFichaConsulta;

    @NotNull(message = "{cliente.not.null}")
    private Cliente cliente;
}