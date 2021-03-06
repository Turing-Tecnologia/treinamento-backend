package com.turingtecnologia.albatroz.backendalbatroz.dto;

import com.turingtecnologia.albatroz.backendalbatroz.model.entities.Cliente;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ConsultaRealizadaDTO {
    @NotBlank(message = "Check In" + "{not.blank}")
    private Date checkInConsultaRealizada;

    @NotBlank(message = "Check out" + "{not.blank}")
    private Date checkOutConsultaRealizada;

    @NotBlank(message = "Data da consulta" + "{not.blank}")
    private Calendar dataConsultaRealizada;

    @NotBlank(message = "Especialidade" + "{not.blank}")
    private String especialidadeConsultaRealizada;

    @NotBlank(message = "Número da ficha" + "{not.blank}")
    private int numeroFichaConsultaRealizada;

    @NotNull(message = "{cliente.not.null}")
    private Cliente cliente;
}
