package br.com.ohara.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ohara.model.Usuario;
import br.com.ohara.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping({"/", ""})
    public ResponseEntity<Usuario> inserir(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.inserir(usuario));
    }

    @GetMapping({"/", ""})
    public ResponseEntity<List<Usuario>> consultarTodos() {
        return ResponseEntity.ok(usuarioService.consultarTodos());
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Usuario> consultarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.consultarPorId(id));
    }

    @PutMapping({"/", ""})
    public ResponseEntity<Usuario> alterar(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.alterar(usuario));
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Usuario> excluir(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.excluir(id));
    }
    
    @GetMapping("/{email}/{senha}/autenticar")
    public ResponseEntity<Usuario> autenticar(@PathVariable String email, @PathVariable String senha) {
        return ResponseEntity.ok(usuarioService.autenticar(email, senha));
    }
}
