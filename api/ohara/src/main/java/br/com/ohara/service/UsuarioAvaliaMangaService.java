package br.com.ohara.service;

import org.jdbi.v3.core.Jdbi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.ohara.dao.UsuarioAvaliaMangaDao;
import br.com.ohara.model.UsuarioAvaliaManga;

@Service
public class UsuarioAvaliaMangaService {
	private final UsuarioAvaliaMangaDao usuarioAvaliaMangaDao;

    public UsuarioAvaliaMangaService(Jdbi jdbi) {
        this.usuarioAvaliaMangaDao = jdbi.onDemand(UsuarioAvaliaMangaDao.class);
    }

    public UsuarioAvaliaManga inserir(UsuarioAvaliaManga u) {
        Long id = usuarioAvaliaMangaDao.inserir(u);
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao gerar ID para a nova avaliação.");
        }

        return u;
    }

    public UsuarioAvaliaManga alterar(UsuarioAvaliaManga uam) {
        Long idManga = uam.getIdManga();
        if (idManga == null) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "id Mangá é informação obrigatória.");
        }
        
        Long idUsuario = uam.getIdUsuario();
        if (idUsuario == null) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "id Usuário é informação obrigatória.");
        }

        UsuarioAvaliaManga uAux = usuarioAvaliaMangaDao.consultarPorId(idUsuario, idManga);
        if (uAux == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliação não encontrada.");
        }

        Integer qtd = usuarioAvaliaMangaDao.alterar(uam);
        if (qtd == null || qtd != 1) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A quantidade de entidades alteradas é " + qtd + ".");
        }

        uAux = usuarioAvaliaMangaDao.consultarPorId(idUsuario, idManga);
        return uAux;
    }

    public UsuarioAvaliaManga excluir(Long idUsuario, Long idManga) {
    	UsuarioAvaliaManga uAux = usuarioAvaliaMangaDao.consultarPorId(idUsuario, idManga);
        if (uAux == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliação não encontrada.");
        }

        Integer qtd = usuarioAvaliaMangaDao.excluir(idUsuario, idManga);

        if (qtd != 1) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A quantidade de entidades excluídas é " + qtd + ".");
        }

        return uAux;
    }
}
