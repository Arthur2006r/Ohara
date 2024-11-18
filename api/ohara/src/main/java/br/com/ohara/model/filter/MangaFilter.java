package br.com.ohara.model.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MangaFilter {

	private Long idManga;

	private String titulo;
	private String autor;
	private String sinopse;
	private String capa;
	private String anoDePublicacao;
	private int qtdDeVolumes;
	private int qtdDeCapitulos;
	private int popularidade;
	private String status;
}
