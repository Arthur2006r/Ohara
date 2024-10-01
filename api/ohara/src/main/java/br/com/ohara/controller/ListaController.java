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

import br.com.ohara.model.Lista;
import br.com.ohara.service.ListaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/lista")
public class ListaController {
	private final ListaService listaService;

    public ListaController(ListaService listaService) {
        this.listaService = listaService;
    }

    @PostMapping({"/", ""})
    public ResponseEntity<Lista> inserir(@Valid @RequestBody Lista lista) {
        return ResponseEntity.ok(listaService.inserir(lista));
    }

    @GetMapping({"/{idUsuario}"})
    public ResponseEntity<List<Lista>> consultarTodos(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(listaService.consultarTodosUsuario(idUsuario));
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Lista> consultarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(listaService.consultarPorId(id));
    }

    @PutMapping({"/", ""})
    public ResponseEntity<Lista> alterar(@Valid @RequestBody Lista lista) {
        return ResponseEntity.ok(listaService.alterar(lista));
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Lista> excluir(@PathVariable Long id) {
        return ResponseEntity.ok(listaService.excluir(id));
    }
}
