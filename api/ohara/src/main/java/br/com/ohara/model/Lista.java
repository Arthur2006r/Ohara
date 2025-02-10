package br.com.ohara.model;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Lista {
	private Long idLista;
	
	@NotNull 
	private Usuario usuario;

	@NotNull
	@NotBlank(message = "Titulo é obrigatório")
	private String titulo;
	
	private List<Manga> mangas;
}
