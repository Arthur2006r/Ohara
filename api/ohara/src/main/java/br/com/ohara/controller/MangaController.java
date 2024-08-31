package br.com.ohara.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ohara.model.Manga;
import br.com.ohara.service.MangaService;
import jakarta.validation.Valid;

@RestController  
@RequestMapping("/api/v1/manga")
public class MangaController {

    private final MangaService mangaService;

    public MangaController(MangaService mangaService) {
        this.mangaService = mangaService;
    }

    @PostMapping({"/", ""})
    public ResponseEntity<Manga> inserir(@Valid @RequestBody Manga m) {
        return ResponseEntity.ok(mangaService.inserir(m));
    }

    @GetMapping({"/", ""})
    public ResponseEntity<List<Manga>> consultar() {
        return ResponseEntity.ok(mangaService.consultarTodos());
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Manga> consultarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(mangaService.consultarPorId(id));
    }
    
    @PutMapping({"/", ""})
    public ResponseEntity<Manga> alterar(@Valid @RequestBody Manga m) {
        return ResponseEntity.ok(mangaService.alterar(m));
    }
    
    @DeleteMapping({"/{id}"})
    public ResponseEntity<Manga> excluir(@PathVariable Long id) {
        return ResponseEntity.ok(mangaService.excluir(id));
    }
}