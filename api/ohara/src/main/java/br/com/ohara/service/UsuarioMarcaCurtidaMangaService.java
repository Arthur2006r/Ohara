package br.com.ohara.service;

import org.jdbi.v3.core.Jdbi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.ohara.dao.UsuarioMarcaCurtidaMangaDao;
import br.com.ohara.model.UsuarioMarcaCurtidaManga;

@Service
public class UsuarioMarcaCurtidaMangaService {
	private final UsuarioMarcaCurtidaMangaDao usuarioMarcaCurtidaMangaDao;

    public UsuarioMarcaCurtidaMangaService(Jdbi jdbi) {
        this.usuarioMarcaCurtidaMangaDao = jdbi.onDemand(UsuarioMarcaCurtidaMangaDao.class);
    }

    public UsuarioMarcaCurtidaManga inserir(UsuarioMarcaCurtidaManga umcm) {   	
        Long id = usuarioMarcaCurtidaMangaDao.inserir(umcm);
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao gerar ID para a nova curtida.");
        }

        return umcm;
    }
    
    public UsuarioMarcaCurtidaManga excluir(Long idUsuario, Long idManga) {
    	UsuarioMarcaCurtidaManga uAux = usuarioMarcaCurtidaMangaDao.consultarPorId(idUsuario, idManga);
        if (uAux == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Curtida não encontrada.");
        }

        Integer qtd = usuarioMarcaCurtidaMangaDao.excluir(idUsuario, idManga);

        if (qtd != 1) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A quantidade de entidades excluídas é " + qtd + ".");
        }

        return uAux;
    }
}
