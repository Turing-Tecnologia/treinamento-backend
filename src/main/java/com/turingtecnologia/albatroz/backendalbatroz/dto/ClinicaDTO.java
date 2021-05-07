package com.turingtecnologia.albatroz.backendalbatroz.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CNPJ;

import lombok.Data;

@Data
public class ClinicaDTO {
    @NotBlank(message = "CNPJ {not.blank}")
    @CNPJ(message = "{cnpj.not.valid}")
    String cnpjClinica;

    @NotBlank(message = "Nome da clínica {not.blank}")
    String nomeClinica;

    @NotBlank(message = "Endereço {not.blank}")
    String enderecoClinica;

    @NotBlank(message = "E-mail {not.blank}")
    @Email(message = "{email.not.valid}")
    String emailClinica;
}
