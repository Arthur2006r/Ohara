package br.com.ohara.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ohara.model.UsuarioComentaManga;
import br.com.ohara.service.UsuarioComentaMangaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/UsuarioComentarioManga")
public class UsuarioComentaMangaController {
	private final UsuarioComentaMangaService usuarioComentaMangaService;

	public UsuarioComentaMangaController(UsuarioComentaMangaService usuarioComentaMangaService) {
		this.usuarioComentaMangaService = usuarioComentaMangaService;
	}

	@PostMapping({ "/", "" })
	public ResponseEntity<UsuarioComentaManga> inserir(@Valid @RequestBody UsuarioComentaManga usuarioComentaManga) {
		return ResponseEntity.ok(usuarioComentaMangaService.inserir(usuarioComentaManga));
	}

	@DeleteMapping({ "/{idUsuarioComentaManga}" })
	public ResponseEntity<UsuarioComentaManga> excluir(@PathVariable Long idUsuarioComentaManga) {
		return ResponseEntity.ok(usuarioComentaMangaService.excluir(idUsuarioComentaManga));
	}
}
