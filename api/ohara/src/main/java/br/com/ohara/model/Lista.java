package br.com.ohara.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Lista {
	private Long idManga;
	
	@NotNull 
	private Long idUsuario;

	@NotNull
	@NotBlank(message = "Titulo é obrigatório")
	private String titulo;
}
