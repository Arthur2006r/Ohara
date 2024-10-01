package br.com.ohara.service;

import org.jdbi.v3.core.Jdbi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.ohara.dao.UsuarioComentaMangaDao;
import br.com.ohara.model.UsuarioComentaManga;

@Service
public class UsuarioComentaMangaService {
	private final UsuarioComentaMangaDao usuarioComentaMangaDao;

    public UsuarioComentaMangaService(Jdbi jdbi) {
        this.usuarioComentaMangaDao = jdbi.onDemand(UsuarioComentaMangaDao.class);
    }

    public UsuarioComentaManga inserir(UsuarioComentaManga ucm) {
    	if (ucm.getIdUsuarioComentaManga() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID deve ser nulo ao inserir um novo comentário.");
        }
    	
        Long id = usuarioComentaMangaDao.inserir(ucm);
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao gerar ID para o novo comentário.");
        }

        return ucm;
    }
    
    public UsuarioComentaManga excluir(Long idUsuarioComentaManga) {
    	UsuarioComentaManga uAux = usuarioComentaMangaDao.consultarPorId(idUsuarioComentaManga);
        if (uAux == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comentário não encontrada.");
        }

        Integer qtd = usuarioComentaMangaDao.excluir(idUsuarioComentaManga);

        if (qtd != 1) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A quantidade de entidades excluídas é " + qtd + ".");
        }

        return uAux;
    }
}
