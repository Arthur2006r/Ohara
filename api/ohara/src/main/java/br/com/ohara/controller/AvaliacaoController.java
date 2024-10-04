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

import br.com.ohara.model.Avaliacao;
import br.com.ohara.model.Review;
import br.com.ohara.service.AvaliacaoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/avaliacao")
public class AvaliacaoController {
	private final AvaliacaoService avaliacaoService;

	public AvaliacaoController(AvaliacaoService avaliacaoService) {
		this.avaliacaoService = avaliacaoService;
	}

	@PostMapping({ "/", "" })
	public ResponseEntity<Avaliacao> inserir(@Valid @RequestBody Avaliacao avaliacao) {
		return ResponseEntity.ok(avaliacaoService.inserir(avaliacao));
	}

	@PutMapping({ "/", "" })
	public ResponseEntity<Avaliacao> alterar(@Valid @RequestBody Avaliacao avaliacao) {
		return ResponseEntity.ok(avaliacaoService.alterar(avaliacao));
	}
	
	@GetMapping({"/", ""})
    public ResponseEntity<List<Avaliacao>> consultarTodos() {
        return ResponseEntity.ok(avaliacaoService.consultarTodos());
    }
	
	@GetMapping({"/todos", "/{idManga}"})
    public ResponseEntity<List<Avaliacao>> consultarTodos(@PathVariable Long idManga) {
        return ResponseEntity.ok(avaliacaoService.consultarTodosManga(idManga));
    }
	
	@GetMapping({"/seguidos/{idManga}/{idUsuario}"})
    public ResponseEntity<List<Avaliacao>> consultarSeguidos(@PathVariable Long idManga, @PathVariable Long idUsuario) {
        return ResponseEntity.ok(avaliacaoService.consultarSeguidosManga(idManga, idUsuario));
    }
	
	@GetMapping({"/minha/{idManga}/{idUsuario}"})
    public ResponseEntity<List<Avaliacao>> consultarMinha(@PathVariable Long idManga, @PathVariable Long idUsuario) {
        return ResponseEntity.ok(avaliacaoService.consultarMinha(idManga, idUsuario));
    }

	@DeleteMapping({ "/{idUsuario}/{idManga}" })
	public ResponseEntity<Avaliacao> excluir(@PathVariable Long idUsuario, Long idManga) {
		return ResponseEntity.ok(avaliacaoService.excluir(idUsuario, idManga));
	}
}
