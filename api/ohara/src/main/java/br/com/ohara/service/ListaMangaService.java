package br.com.ohara.service;

import java.util.List;

import org.jdbi.v3.core.Jdbi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.ohara.dao.ListaMangaDao;
import br.com.ohara.model.ListaManga;
import br.com.ohara.model.Usuario;

@Service
public class ListaMangaService {
	private final ListaMangaDao listaMangaDao;

    public ListaMangaService(Jdbi jdbi) {
        this.listaMangaDao = jdbi.onDemand(ListaMangaDao.class);
    }

    public ListaManga inserir(ListaManga lm) {
    	if (lm.getIdListaManga() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID deve ser nulo ao inserir uma nova associação ListaManga.");
        }
    	
        Long id = listaMangaDao.inserir(lm);
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao gerar ID para o novo comentário.");
        }

        return lm;
    }
    
    public List<ListaManga> consultarPorLista(Long idLista) {
        return listaMangaDao.consultarPorLista(idLista);
    }

    public ListaManga consultarPorId(Long id) {
    	ListaManga listaManga = listaMangaDao.consultarPorId(id);
        if (listaManga == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Associação ListaManga não encontrada com o id: " + id + ".");
        }
        return listaManga;
    }
    
    public ListaManga excluir(Long idUsuarioComentaManga) {
    	ListaManga uAux = listaMangaDao.consultarPorId(idUsuarioComentaManga);
        if (uAux == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comentário não encontrada.");
        }

        Integer qtd = listaMangaDao.excluir(idUsuarioComentaManga);

        if (qtd != 1) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A quantidade de entidades excluídas é " + qtd + ".");
        }

        return uAux;
    }
}
