package br.com.ohara.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ohara.model.Email;
import br.com.ohara.service.EnviarEmailService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/email")
public class EnviarEmailController {

	private final EnviarEmailService enviarEmailService;

	public EnviarEmailController(EnviarEmailService enviarEmailService) {
		this.enviarEmailService = enviarEmailService;
	}

	@PostMapping("/simples")
	public void enviarMensagemSimples(@Valid @RequestBody Email email) {
		enviarEmailService.enviarMensagemSimples(email);
	}
}
