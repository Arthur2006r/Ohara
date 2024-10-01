package br.com.ohara.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ohara.model.UsuarioAvaliaManga;
import br.com.ohara.service.UsuarioAvaliaMangaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/UsuarioAvaliacaoManga")
public class UsuarioAvaliaMangaController {
	private final UsuarioAvaliaMangaService usuarioAvaliaMangaService;

	public UsuarioAvaliaMangaController(UsuarioAvaliaMangaService usuarioService) {
		this.usuarioAvaliaMangaService = usuarioService;
	}

	@PostMapping({ "/", "" })
	public ResponseEntity<UsuarioAvaliaManga> inserir(@Valid @RequestBody UsuarioAvaliaManga usuarioAvaliaManga) {
		return ResponseEntity.ok(usuarioAvaliaMangaService.inserir(usuarioAvaliaManga));
	}

	@PutMapping({ "/", "" })
	public ResponseEntity<UsuarioAvaliaManga> alterar(@Valid @RequestBody UsuarioAvaliaManga usuarioAvaliaManga) {
		return ResponseEntity.ok(usuarioAvaliaMangaService.alterar(usuarioAvaliaManga));
	}

	@DeleteMapping({ "/{idUsuario}/{idManga}" })
	public ResponseEntity<UsuarioAvaliaManga> excluir(@PathVariable Long idUsuario, Long idManga) {
		return ResponseEntity.ok(usuarioAvaliaMangaService.excluir(idUsuario, idManga));
	}
}
