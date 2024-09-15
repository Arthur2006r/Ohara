package br.com.alimentadorOhara.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alimentadorOhara.model.Manga;
import br.com.alimentadorOhara.service.AlimentadorOharaService;
import jakarta.validation.Valid;

@RestController  
@RequestMapping("/api/v1/alimentar/sistema")
public class AlimentadorOharaController {

    private final AlimentadorOharaService alimentadorOharaService;

    public AlimentadorOharaController(AlimentadorOharaService alimentadorOharaService) {
        this.alimentadorOharaService = alimentadorOharaService;
    }

    @PostMapping({"/", ""})
    public ResponseEntity<List<Manga>> inserirMangas(@Valid @RequestBody List<Manga> mangas) {
        return ResponseEntity.ok(alimentadorOharaService.inserirMangas(mangas));
    }
}
