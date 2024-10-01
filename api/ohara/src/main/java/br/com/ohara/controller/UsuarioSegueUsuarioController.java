package br.com.ohara.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ohara.model.UsuarioSegueUsuario;
import br.com.ohara.service.UsuarioSegueUsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/UsuarioSegueUsuario")
public class UsuarioSegueUsuarioController {
	private final UsuarioSegueUsuarioService usuarioSegueUsuarioService;

	public UsuarioSegueUsuarioController(UsuarioSegueUsuarioService usuarioSegueUsuarioService) {
		this.usuarioSegueUsuarioService = usuarioSegueUsuarioService;
	}

	@PostMapping({ "/", "" })
	public ResponseEntity<UsuarioSegueUsuario> inserir(@Valid @RequestBody UsuarioSegueUsuario usuarioSegueUsuario) {
		return ResponseEntity.ok(usuarioSegueUsuarioService.inserir(usuarioSegueUsuario));
	}

	@DeleteMapping({ "/{idUsuario}/{idManga}" })
	public ResponseEntity<UsuarioSegueUsuario> excluir(@PathVariable Long idUsuario, Long idManga) {
		return ResponseEntity.ok(usuarioSegueUsuarioService.excluir(idUsuario, idManga));
	}
}
