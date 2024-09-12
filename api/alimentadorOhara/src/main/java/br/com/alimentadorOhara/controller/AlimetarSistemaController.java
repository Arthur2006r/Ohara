package br.com.alimentadorOhara.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alimentadorOhara.model.Manga;
import br.com.alimentadorOhara.service.AlimentarSistemaService;
import jakarta.validation.Valid;

@RestController  
@RequestMapping("/api/v1/alimentar/sistema")
public class AlimetarSistemaController {

    private final AlimentarSistemaService alimetarSistemaService;

    public AlimetarSistemaController(AlimentarSistemaService alimetarSistemaService) {
        this.alimetarSistemaService = alimetarSistemaService;
    }

    @PostMapping({"/", ""})
    public ResponseEntity<List<Manga>> inserirMangas(@Valid @RequestBody List<Manga> mangas) {
        return ResponseEntity.ok(alimetarSistemaService.inserirMangas(mangas));
    }
}
