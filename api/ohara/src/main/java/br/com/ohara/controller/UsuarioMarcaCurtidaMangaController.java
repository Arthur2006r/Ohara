package br.com.ohara.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ohara.model.UsuarioMarcaCurtidaManga;
import br.com.ohara.service.UsuarioMarcaCurtidaMangaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/UsuarioMarcaCurtidaManga")
public class UsuarioMarcaCurtidaMangaController {
	private final UsuarioMarcaCurtidaMangaService usuarioMarcaCurtidaMangaService;

	public UsuarioMarcaCurtidaMangaController(UsuarioMarcaCurtidaMangaService usuarioMarcaCurtidaMangaService) {
		this.usuarioMarcaCurtidaMangaService = usuarioMarcaCurtidaMangaService;
	}

	@PostMapping({ "/", "" })
	public ResponseEntity<UsuarioMarcaCurtidaManga> inserir(@Valid @RequestBody UsuarioMarcaCurtidaManga usuarioMarcaCurtidaManga) {
		return ResponseEntity.ok(usuarioMarcaCurtidaMangaService.inserir(usuarioMarcaCurtidaManga));
	}

	@DeleteMapping({ "/{idUsuario}/{idManga}" })
	public ResponseEntity<UsuarioMarcaCurtidaManga> excluir(@PathVariable Long idUsuario, Long idManga) {
		return ResponseEntity.ok(usuarioMarcaCurtidaMangaService.excluir(idUsuario, idManga));
	}
}
