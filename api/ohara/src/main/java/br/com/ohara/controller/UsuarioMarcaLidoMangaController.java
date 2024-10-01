package br.com.ohara.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ohara.model.UsuarioMarcaLidoManga;
import br.com.ohara.service.UsuarioMarcaLidoMangaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/UsuarioMarcaLidoManga")
public class UsuarioMarcaLidoMangaController {
	private final UsuarioMarcaLidoMangaService usuarioMarcaLidoMangaService;

	public UsuarioMarcaLidoMangaController(UsuarioMarcaLidoMangaService usuarioMarcaLidoMangaService) {
		this.usuarioMarcaLidoMangaService = usuarioMarcaLidoMangaService;
	}

	@PostMapping({ "/", "" })
	public ResponseEntity<UsuarioMarcaLidoManga> inserir(@Valid @RequestBody UsuarioMarcaLidoManga usuarioMarcaLidoManga) {
		return ResponseEntity.ok(usuarioMarcaLidoMangaService.inserir(usuarioMarcaLidoManga));
	}

	@DeleteMapping({ "/{idUsuario}/{idManga}" })
	public ResponseEntity<UsuarioMarcaLidoManga> excluir(@PathVariable Long idUsuario, Long idManga) {
		return ResponseEntity.ok(usuarioMarcaLidoMangaService.excluir(idUsuario, idManga));
	}
}
