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
        if (u.getIdUsuario() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID deve ser nulo ao inserir um novo usuário.");
        }

        Long id = usuarioDao.inserir(u);
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao gerar ID para o novo usuário.");
        }

        u.setIdUsuario(id);
        return u;
    }

    public List<Usuario> consultarTodos() {
        return usuarioDao.consultarTodos();
    }

    public Usuario consultarPorId(Long id) {
        Usuario usuario = usuarioDao.consultarPorId(id);
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

        Usuario uAux = usuarioDao.consultarPorId(id);
        if (uAux == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com o id: " + id + ".");
        }

        // Alteração da entidade
        Integer qtd = usuarioDao.alterar(u);
        if (qtd == null || qtd != 1) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A quantidade de entidades alteradas é " + qtd + ".");
        }

        // Retornar a informação alterada no banco de dados.
        uAux = usuarioDao.consultarPorId(id);
        return uAux;
    }

    public Usuario excluir(Long id) {
        // Validações extras das informações
        Usuario uAux = usuarioDao.consultarPorId(id);
        if (uAux == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com o id: " + id + ".");
        }

        // Exclusão da entidade
        Integer qtd = usuarioDao.excluir(id);

        // Validar se a entidade foi excluída corretamente.
        if (qtd != 1) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A quantidade de entidades excluídas é " + qtd + ".");
        }

        return uAux;
    }
    
    public Usuario autenticar(String email, String senha) {
        Usuario usuario = usuarioDao.autenticar(email, senha);
        return usuario;
    }
}
