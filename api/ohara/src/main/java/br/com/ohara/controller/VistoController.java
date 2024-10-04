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
@RequestMapping("/api/v1/visto")
public class VistoController {
	private final LidoService vistoService;

	public VistoController(LidoService vistoService) {
		this.vistoService = vistoService;
	}

	@PostMapping({ "/", "" })
	public ResponseEntity<Lido> inserir(@Valid @RequestBody Lido visto) {
		return ResponseEntity.ok(vistoService.inserir(visto));
	}
	
	@GetMapping({"/", ""})
    public ResponseEntity<List<Lido>> consultarTodos() {
        return ResponseEntity.ok(vistoService.consultarTodos());
    }
	
	@GetMapping({"/meu/{idManga}/{idUsuario}"})
    public ResponseEntity<Lido> consultarMeuLerDepois(@PathVariable Long idManga, @PathVariable Long idUsuario) {
        return ResponseEntity.ok(vistoService.consultarMeuLido(idManga, idUsuario));
    }

	@DeleteMapping({ "/{idUsuario}/{idManga}" })
	public ResponseEntity<Lido> excluir(@PathVariable Long idUsuario, Long idManga) {
		return ResponseEntity.ok(vistoService.excluir(idUsuario, idManga));
	}
}
