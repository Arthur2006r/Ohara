package br.com.ohara.service;

import java.util.List;

import org.jdbi.v3.core.Jdbi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.ohara.dao.UsuarioDao;
import br.com.ohara.model.Usuario;

@Service
public class UsuarioService {

    private final UsuarioDao usuarioDao;

    public UsuarioService(Jdbi jdbi) {
        this.usuarioDao = jdbi.onDemand(UsuarioDao.class);
    }

    public Usuario inserir(Usuario u) {
        if (u.getIdUsuario() != 0) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Id - informação ilegal.");
        }

        Long id = usuarioDao.insert(u);
        u.setIdUsuario(id);
        return u;
    }

    public List<Usuario> consultarTodos() {
        return usuarioDao.getAll();
    }

    public Usuario consultarPorId(Long id) {
        Usuario usuario = usuarioDao.get(id);
        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com o id: " + id + ".");
        }
        return usuario;
    }

    public Usuario alterar(Usuario u) {
        // Validações extras das informações
        Long id = u.getIdUsuario();
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Id é informação obrigatória.");
        }

        Usuario uAux = usuarioDao.get(id);
        if (uAux == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com o id: " + id + ".");
        }

        // Alteração da entidade
        Long qtd = usuarioDao.update(u);

        // Validar se a entidade foi alterada corretamente.
        if (qtd != 1) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A quantidade de entidades alteradas é " + qtd + ".");
        }

        // Retornar a informação alterada no banco de dados.
        uAux = usuarioDao.get(id);
        return uAux;
    }

    public Usuario excluir(Long id) {
        // Validações extras das informações
        Usuario uAux = usuarioDao.get(id);
        if (uAux == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com o id: " + id + ".");
        }

        // Exclusão da entidade
        Long qtd = usuarioDao.delete(id);

        // Validar se a entidade foi excluída corretamente.
        if (qtd != 1) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A quantidade de entidades excluídas é " + qtd + ".");
        }

        return uAux;
    }
    
    public Usuario autenticar(String email, String senha) {
        Usuario usuario = usuarioDao.findByEmailAndSenha(email, senha);
        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com o email e a senha.");
        }
        
        return usuario;
    }
}
