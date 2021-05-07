package com.turingtecnologia.albatroz.backendalbatroz.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CNPJ;

import lombok.Data;

@Data
public class ClinicaDTO {
    @NotBlank(message = "nomeClinica {not.blank}")
    private String nomeClinica;
    @NotBlank(message = "telefone {not.blank}")
    private String telefone;
    @NotBlank(message = "email {not.blank}")
    private String email;
    @CNPJ
    private String cnpj;
}
