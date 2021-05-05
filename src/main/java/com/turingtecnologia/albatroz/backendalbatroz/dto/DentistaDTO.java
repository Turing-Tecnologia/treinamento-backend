package com.turingtecnologia.albatroz.backendalbatroz.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

	@Data
public class DentistaDTO {
	    @NotBlank(message ="Contato " + "{not.blank}")
	    private String contatoDentista;

	    @NotNull(message = "{cpf.not.null}")
	    @Digits(integer = 11, fraction = 0)
	    @DecimalMax(value = "99999999999", message = "{cpf.limit.value}")
	    @DecimalMin(value = "11111111111", message = "{cpf.limit.value}")
	    private String cpfDentista;

	    @NotBlank(message = "Email " + "{not.blank}")
	    @Email(message = "{email.not.valid}")
	    private String emailDentista;

	    @NotBlank(message = "Endereço " + "{not.blank}")
	    private String enderecoDentista;
	    
	    @NotBlank(message = "Nome do dentista " + "{not.blank}")
	    private String nomeDentista;
	

}
