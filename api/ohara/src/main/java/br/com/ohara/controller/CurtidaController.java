package br.com.ohara.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ohara.model.Curtida;
import br.com.ohara.service.CurtidaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/curtida")
public class CurtidaController {
	private final CurtidaService curtidaService;

	public CurtidaController(CurtidaService curtidaService) {
		this.curtidaService = curtidaService;
	}

	@PostMapping({ "/", "" })
	public ResponseEntity<Curtida> inserir(@Valid @RequestBody Curtida curtida) {
		return ResponseEntity.ok(curtidaService.inserir(curtida));
	}
	
	@GetMapping({"/", ""})
    public ResponseEntity<List<Curtida>> consultarTodos() {
        return ResponseEntity.ok(curtidaService.consultarTodos());
    }
	
	@GetMapping({"/minha/{idManga}/{idUsuario}"})
    public ResponseEntity<Curtida> consultarMinhaCurtida(@PathVariable Long idManga, @PathVariable Long idUsuario) {
        return ResponseEntity.ok(curtidaService.consultarMinhaCurtida(idManga, idUsuario));
    }
	
	//
	@GetMapping("/usuario/{idUsuario}")
	public ResponseEntity<List<Curtida>> listarCurtidasPorUsuario(@PathVariable Long idUsuario) {
	    return ResponseEntity.ok(curtidaService.listarCurtidasPorUsuario(idUsuario));
	}

	@DeleteMapping({ "/{idUsuario}/{idManga}" })
	public ResponseEntity<Curtida> excluir(@PathVariable Long idUsuario, @PathVariable Long idManga) {
		return ResponseEntity.ok(curtidaService.excluir(idUsuario, idManga));
	}
}
