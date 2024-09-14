package br.com.ohara.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Manga {
	
	private Long idManga;
	
	@NotNull
	@NotBlank(message = "Titulo é obrigatório")
	private String titulo;
	
	@NotNull
	@NotBlank(message = "Autor é obrigatório")
	private String autor;
	
	@NotNull
	@NotBlank(message = "Sinopse é obrigatório")
	@Size(min=4, max=500)
	private String sinopse;
	
	@NotNull
	private String capa;
	
	@NotNull
	private String anoDePublicacao;
	
	@NotNull
	@Min(value = 1, message = "Quantidade de volumes deve ser maior que zero")
	private int qtdDeVolumes;
	
	@NotNull
	@Min(value = 1, message = "Quantidade de capítulos deve ser maior que zero")
	private int qtdDeCapitulos;
	
	@NotNull
	private int popularidade;
	
	@NotNull
	private String status;
}
