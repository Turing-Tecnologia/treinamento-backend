package com.turingtecnologia.albatroz.backendalbatroz.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UsuarioDTO {
    @NotBlank(message = "username " + "{not.blank}")
    private String username;

    @NotBlank(message = "password " + "{not.blank}")
    private String password;
    
    @NotBlank(message = "Nome completo " + "{not.blank}")
    private String nomeCompleto;
}
