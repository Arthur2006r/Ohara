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

import br.com.ohara.model.Curtida;
import br.com.ohara.model.LerDepois;
import br.com.ohara.model.Usuario;
import br.com.ohara.service.LerDepoisService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/lerDepois")
public class LerDepoisController {
	private final LerDepoisService lerDepoisService;

	public LerDepoisController(LerDepoisService lerDepoisService) {
		this.lerDepoisService = lerDepoisService;
	}

	@PostMapping({ "/", "" })
	public ResponseEntity<LerDepois> inserir(@Valid @RequestBody LerDepois lerDepois) {
		return ResponseEntity.ok(lerDepoisService.inserir(lerDepois));
	}
	
	@GetMapping({"/", ""})
    public ResponseEntity<List<LerDepois>> consultarTodos() {
        return ResponseEntity.ok(lerDepoisService.consultarTodos());
    }
	
	@GetMapping({"/meu/{idManga}/{idUsuario}"})
    public ResponseEntity<LerDepois> consultarMeuLerDepois(@PathVariable Long idManga, @PathVariable Long idUsuario) {
        return ResponseEntity.ok(lerDepoisService.consultarMeuLerDepois(idManga, idUsuario));
    }
	
	//
	@GetMapping("/usuario/{idUsuario}")
	public ResponseEntity<List<LerDepois>> listarCurtidasPorUsuario(@PathVariable Long idUsuario) {
	    return ResponseEntity.ok(lerDepoisService.listarLerDepoisPorUsuario(idUsuario));
	}

	@DeleteMapping({ "/{idUsuario}/{idManga}" })
	public ResponseEntity<LerDepois> excluir(@PathVariable Long idUsuario, @PathVariable Long idManga) {
		return ResponseEntity.ok(lerDepoisService.excluir(idUsuario, idManga));
	}
}
