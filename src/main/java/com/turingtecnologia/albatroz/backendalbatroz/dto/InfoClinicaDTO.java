package com.turingtecnologia.albatroz.backendalbatroz.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InfoClinicaDTO {
    private String nomeClinica;
    private String telefone;
    private String email;
    private String cnpj;
    private List<InfoConsultaDTO> consultas;
    private List<InfoConsultaDTO> consultasRealizadas;
}
