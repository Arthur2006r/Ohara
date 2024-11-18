package br.com.ohara.model;

import lombok.Data;

@Data
public class Email {
	private String para;
	private String assunto;
	private String texto;
	private String caminhoParaAnexo;
}	
