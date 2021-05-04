package com.turingtecnologia.albatroz.backendalbatroz.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ClienteDTO {
    @NotBlank(message ="Contato " + "{not.blank}")
    private String contatoCliente;

    @NotNull(message = "{cpf.not.null}")
    @Digits(integer = 11, fraction = 0)
    @DecimalMax(value = "99999999999", message = "{cpf.limit.value}")
    @DecimalMin(value = "11111111111", message = "{cpf.limit.value}")
    private String cpfCliente;

    @NotBlank(message = "Email " + "{not.blank}")
    @Email(message = "{email.not.valid}")
    private String emailCliente;

    @NotBlank(message = "Endereço " + "{not.blank}")
    private String enderecoCliente;
    
    @NotBlank(message = "Nome do cliente " + "{not.blank}")
    private String nomeCliente;
}
