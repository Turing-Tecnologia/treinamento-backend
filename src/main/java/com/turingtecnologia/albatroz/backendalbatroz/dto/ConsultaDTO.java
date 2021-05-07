package com.turingtecnologia.albatroz.backendalbatroz.dto;

import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Cliente;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Clinica;

import lombok.Data;

import java.util.Calendar;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ConsultaDTO {
    @NotNull(message ="Check In " + "{not.blank}")
    private Date checkInConsulta;

    @NotNull(message = "Check Out " + "{not.blank}")
    private Date checkOutConsulta;

    @NotNull(message = "Data da consulta " + "{not.blank}")
    private Calendar dataConsulta;

    @NotNull(message = "Clinica " + "{not.blank}")
    private Clinica clinica;

    @NotBlank(message = "Especialidade da consulta " + "{not.blank}")
    private String especialidadeConsulta;

    @NotNull(message = "Numero da Ficha da consulta " + "{not.blank}")
    private int numeroFichaConsulta;

    @NotNull(message = "{cliente.not.null}")
    private Cliente cliente;
}