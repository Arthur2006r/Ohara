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

import br.com.ohara.model.LerDepois;
import br.com.ohara.model.Lido;
import br.com.ohara.service.LidoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/lido")
public class LidoController {
	private final LidoService lidoService;

	public LidoController(LidoService vistoService) {
		this.lidoService = vistoService;
	}

	@PostMapping({ "/", "" })
	public ResponseEntity<Lido> inserir(@Valid @RequestBody Lido lido) {
		return ResponseEntity.ok(lidoService.inserir(lido));
	}
	
	@GetMapping({"/", ""})
    public ResponseEntity<List<Lido>> consultarTodos() {
        return ResponseEntity.ok(lidoService.consultarTodos());
    }
	
	@GetMapping({"/meu/{idManga}/{idUsuario}"})
    public ResponseEntity<Lido> consultarMeuLido(@PathVariable Long idManga, @PathVariable Long idUsuario) {
        return ResponseEntity.ok(lidoService.consultarMeuLido(idManga, idUsuario));
    }

	@DeleteMapping({ "/{idUsuario}/{idManga}" })
	public ResponseEntity<Lido> excluir(@PathVariable Long idUsuario, @PathVariable Long idManga) {
		return ResponseEntity.ok(lidoService.excluir(idUsuario, idManga));
	}
}
