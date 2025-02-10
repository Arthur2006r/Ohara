package br.com.ohara.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ListaManga {
	@NotNull
	private Long idLista;

	@NotNull
	private long idManga;
}
