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

import br.com.ohara.model.Review;
import br.com.ohara.model.Usuario;
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
	
	@GetMapping({"/isSeguidor/{idUsuario}/{idUsuarioSeguido}"})
    public ResponseEntity<List<Usuario>> consultarSeguidores(@PathVariable Long idUsuario, @PathVariable Long idUsuarioSeguido) {
        return ResponseEntity.ok(usuarioSegueUsuarioService.consultarSeguidores(idUsuario));
    }
	
	@GetMapping({"/seguidores/{idUsuario}"})
    public ResponseEntity<List<Usuario>> consultarSeguidores(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(usuarioSegueUsuarioService.consultarSeguidores(idUsuario));
    }
	
	@GetMapping({"/seguidos/{idUsuario}"})
    public ResponseEntity<List<Usuario>> consultarSeguidos(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(usuarioSegueUsuarioService.consultarSeguidores(idUsuario));
    }
}
