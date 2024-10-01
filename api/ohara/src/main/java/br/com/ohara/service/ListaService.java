package br.com.ohara.service;

import java.util.List;

import org.jdbi.v3.core.Jdbi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.ohara.dao.ListaDao;
import br.com.ohara.model.Lista;
import br.com.ohara.model.Usuario;

@Service
public class ListaService {
	private final ListaDao listaDao;

    public ListaService(Jdbi jdbi) {
        this.listaDao = jdbi.onDemand(ListaDao.class);
    }

    public Lista inserir(Lista l) {
        if (l.getIdUsuario() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID deve ser nulo ao inserir uma nova lista.");
        }

        Long id = listaDao.inserir(l);
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao gerar ID para a nova lista.");
        }

        l.setIdUsuario(id);
        return l;
    }

    public List<Lista> consultarTodosUsuario(Long idUsuario) {
        return listaDao.consultarTodosUsuario(idUsuario);
    }

    public Lista consultarPorId(Long id) {
        Lista lista = listaDao.consultarPorId(id);
        if (lista == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lista não encontrada com o id: " + id + ".");
        }
        return lista;
    }

    public Lista alterar(Lista l) {
        Long id = l.getIdUsuario();
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Id é informação obrigatória.");
        }

        Lista lAux = listaDao.consultarPorId(id);
        if (lAux == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com o id: " + id + ".");
        }

        Integer qtd = listaDao.alterar(l);
        if (qtd == null || qtd != 1) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A quantidade de entidades alteradas é " + qtd + ".");
        }

        lAux = listaDao.consultarPorId(id);
        return lAux;
    }

    public Lista excluir(Long id) {
        Lista lAux = listaDao.consultarPorId(id);
        if (lAux == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com o id: " + id + ".");
        }

        Integer qtd = listaDao.excluir(id);

        if (qtd != 1) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A quantidade de entidades excluídas é " + qtd + ".");
        }

        return lAux;
    }
}
