package com.turingtecnologia.albatroz.backendalbatroz.dto;

import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Cliente;
import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Clinica;

import lombok.Data;

import java.util.Calendar;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ConsultaRealizadaDTO {
    @NotNull(message = "Check In" + "{not.blank}")
    private Date checkInConsultaRealizada;

    @NotNull(message = "Check out" + "{not.blank}")
    private Date checkOutConsultaRealizada;

    @NotNull(message = "Data da consulta" + "{not.blank}")
    private Calendar dataConsultaRealizada;

    @NotBlank(message = "Especialidade" + "{not.blank}")
    private String especialidadeConsultaRealizada;

    @NotNull(message = "Número da ficha" + "{not.blank}")
    private int numeroFichaConsultaRealizada;

    @NotNull(message = "{cliente.not.null}")
    private Cliente cliente;

    @NotNull(message = "{clinica.not.null}")
    private Clinica clinica;
}
