package com.turingtecnologia.albatroz.backendalbatroz.dto;

import java.util.Calendar;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InfoConsultaDTO {
    
    private Date checkInConsulta;
    private Date checkOutConsulta;
    private Calendar dataConsulta;
    private String nomeClinica;
    private String especialidadeConsulta;
    private int numeroFichaConsulta;
    private String nomeCliente;
    private String cpfCliente;
    private String contato;
}
