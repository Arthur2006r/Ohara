package br.com.ohara.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ohara.model.ListaManga;
import br.com.ohara.model.Manga;
import br.com.ohara.service.ListaMangaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/listaManga")
public class ListaMangaController {

	private final ListaMangaService listaMangaService;

	public ListaMangaController(ListaMangaService listaMangaService) {
		this.listaMangaService = listaMangaService;
	}

	@PostMapping({ "/", "" })
	public ResponseEntity<ListaManga> inserir(@Valid @RequestBody ListaManga listaManga) {
		return ResponseEntity.ok(listaMangaService.inserir(listaManga));
	}

	@DeleteMapping({ "/{idLista}", "/{idManga}" })
	public ResponseEntity<ListaManga> excluir(@PathVariable Long idLista, @PathVariable Long idManga) {
		return ResponseEntity.ok(listaMangaService.excluir(idLista, idManga));
	}
	
	@GetMapping({ "/idLista", "" })
	public ResponseEntity<List<Manga>> consultarMangasLista(@PathVariable Long idLista) {
		return ResponseEntity.ok(listaMangaService.consultarMangasLista(idLista));
	}
}
