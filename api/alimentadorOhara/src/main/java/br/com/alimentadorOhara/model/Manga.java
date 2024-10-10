package br.com.alimentadorOhara.model;

import lombok.Data;

@Data
public class Manga {

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
