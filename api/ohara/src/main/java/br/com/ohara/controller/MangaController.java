package br.com.ohara.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ohara.model.Manga;
import br.com.ohara.model.filter.MangaFilter;
import br.com.ohara.service.MangaService;

@RestController
@RequestMapping("/api/v1/manga")
public class MangaController {

	private final MangaService mangaService;

	public MangaController(MangaService mangaService) {
		this.mangaService = mangaService;
	}

	@GetMapping({ "/", "" })
	public ResponseEntity<List<Manga>> consultarTodos() {
		return ResponseEntity.ok(mangaService.consultarTodos());
	}

	@GetMapping({ "/top10" })
	public ResponseEntity<List<Manga>> consultarTop10() {
		return ResponseEntity.ok(mangaService.consultarTop10());
	}

	@GetMapping("/curtidos/{idUsuario}")
	public ResponseEntity<List<Manga>> consultarCurtidosUsuario(@PathVariable Long idUsuario) {
		return ResponseEntity.ok(mangaService.consultarCurtidosUsuario(idUsuario));
	}

	@GetMapping("/lerDepois/{idUsuario}")
	public ResponseEntity<List<Manga>> consultarLerDepoisUsuario(@PathVariable Long idUsuario) {
		return ResponseEntity.ok(mangaService.consultarLerDepoisUsuario(idUsuario));
	}

	@GetMapping({ "/{id}" })
	public ResponseEntity<Manga> consultarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(mangaService.consultarPorId(id));
	}
	
	@GetMapping({ "/nota/{id}" })
	public ResponseEntity<Double> calcularNota(@PathVariable Long id) {
		return ResponseEntity.ok(mangaService.calcularNota(id));
	}

	// !! DBF - Busca por filtro
	@RequestMapping(value = "/filtro", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<List<Manga>> consultarPorFiltro(@RequestBody MangaFilter mangaFilter) {
		return ResponseEntity.ok(mangaService.consultarPorFiltro(mangaFilter));
	}
}
