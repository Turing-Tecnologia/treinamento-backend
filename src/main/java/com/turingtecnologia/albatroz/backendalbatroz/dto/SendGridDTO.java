package com.turingtecnologia.albatroz.backendalbatroz.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SendGridDTO {
    @NotBlank(message = "Email " + "{not.blank}")
    @Email(message = "{email.not.valid}")
    public String Email;

    @NotBlank(message = "Assunto " + "{not.blank}")
    public String Assunto;

    @NotBlank(message = "Messagem " + "{not.blank}")
    public String Menssagem;
}