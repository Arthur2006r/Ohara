package br.com.ohara.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ohara.model.UsuarioMarcaLerDepoisManga;
import br.com.ohara.service.UsuarioMarcaLerDepoisMangaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/UsuarioMarcaLerDepoisManga")
public class UsuarioMarcaLerDepoisMangaController {
	private final UsuarioMarcaLerDepoisMangaService usuarioMarcaLerDepoisMangaService;

	public UsuarioMarcaLerDepoisMangaController(UsuarioMarcaLerDepoisMangaService usuarioMarcaLerDepoisMangaService) {
		this.usuarioMarcaLerDepoisMangaService = usuarioMarcaLerDepoisMangaService;
	}

	@PostMapping({ "/", "" })
	public ResponseEntity<UsuarioMarcaLerDepoisManga> inserir(@Valid @RequestBody UsuarioMarcaLerDepoisManga usuarioMarcaLerDepoisManga) {
		return ResponseEntity.ok(usuarioMarcaLerDepoisMangaService.inserir(usuarioMarcaLerDepoisManga));
	}

	@DeleteMapping({ "/{idUsuario}/{idManga}" })
	public ResponseEntity<UsuarioMarcaLerDepoisManga> excluir(@PathVariable Long idUsuario, Long idManga) {
		return ResponseEntity.ok(usuarioMarcaLerDepoisMangaService.excluir(idUsuario, idManga));
	}
}
