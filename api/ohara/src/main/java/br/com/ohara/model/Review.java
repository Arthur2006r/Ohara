package br.com.ohara.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Review {
	private Long idReview;
	
	@NotNull
	private Usuario usuario;
	
	@NotNull
	private Long idManga;
	
	@NotBlank
	@Size(max = 500, message = "A descricao não pode exceder 500 caracteres")
	private String descricao;

}
