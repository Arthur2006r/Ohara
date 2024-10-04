package br.com.ohara.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ohara.model.Manga;
import br.com.ohara.service.MangaService;

@RestController  
@RequestMapping("/api/v1/manga")
public class MangaController {

    private final MangaService mangaService;

    public MangaController(MangaService mangaService) {
        this.mangaService = mangaService;
    }

    @GetMapping({"/", ""})
    public ResponseEntity<List<Manga>> consultarTodos() {
        return ResponseEntity.ok(mangaService.consultarTodos());
    }
    
    @GetMapping("/curtidos/{idUsuario}")
    public ResponseEntity<List<Manga>> consultarCurtidosUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(mangaService.consultarCurtidosUsuario(idUsuario));
    }
    
    @GetMapping("/lerDepois/{idUsuario}")
    public ResponseEntity<List<Manga>> consultarLerDepoisUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(mangaService.consultarLerDepoisUsuario(idUsuario));
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Manga> consultarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(mangaService.consultarPorId(id));
    }
}
