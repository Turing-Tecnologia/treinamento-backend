package com.turingtecnologia.albatroz.backendalbatroz.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ClinicaDTO {
    @NotBlank
    private String nomeClinica;
    @NotBlank
    private String telefone;
    @NotBlank
    private String email;
    @NotBlank
    private String cnpj;
}
