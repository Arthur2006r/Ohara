package br.com.ohara.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Avaliacao {
	@NotNull
	private Long idUsuario;
	
	private Usuario usuario;
	
	@NotNull
	private Long idManga;
	
	@Min(value = 0, message = "A nota não deve ser menor que zero")
	@Max(value = 5, message = "A nota não deve ser maior que cinco")
	private double nota;
}
