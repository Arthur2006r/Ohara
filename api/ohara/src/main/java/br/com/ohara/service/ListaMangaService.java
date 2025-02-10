package br.com.ohara.service;

import java.util.List;

import org.jdbi.v3.core.Jdbi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.ohara.dao.ListaMangaDao;
import br.com.ohara.model.ListaManga;
import br.com.ohara.model.Manga;
import br.com.ohara.model.Usuario;

@Service
public class ListaMangaService {
	private final ListaMangaDao listaMangaDao;

    public ListaMangaService(Jdbi jdbi) {
        this.listaMangaDao = jdbi.onDemand(ListaMangaDao.class);
    }

    public ListaManga inserir(ListaManga lm) {
        listaMangaDao.inserir(lm);

        return lm;
    }
    
    public ListaManga excluir(Long idLista, Long idManga) {
    	ListaManga lm = listaMangaDao.consultarPorId(idLista, idManga);
        if (lm == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Associação ListaManga não encontrada.");
        }

        Integer qtd = listaMangaDao.excluir(idLista, idManga);

        if (qtd != 1) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A quantidade de entidades excluídas é " + qtd + ".");
        }

        return lm;
    }
	
	public List<Manga> consultarMangasLista(Long idLista) {
		return listaMangaDao.consultarMangasLista(idLista);
	}
}
