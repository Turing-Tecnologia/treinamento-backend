package com.turingtecnologia.albatroz.backendalbatroz.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CNPJ;

import lombok.Data;

@Data
public class ClinicaDTO {
    @NotBlank
    private String nomeClinica;
    @NotBlank
    private String telefone;
    @NotBlank
    private String email;
    @CNPJ
    private String cnpj;
}
