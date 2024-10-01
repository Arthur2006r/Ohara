package br.com.ohara.service;

import org.jdbi.v3.core.Jdbi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.ohara.dao.UsuarioMarcaLidoMangaDao;
import br.com.ohara.model.UsuarioMarcaLidoManga;

@Service
public class UsuarioMarcaLidoMangaService {
	private final UsuarioMarcaLidoMangaDao usuarioMarcaLidoMangaDao;

    public UsuarioMarcaLidoMangaService(Jdbi jdbi) {
        this.usuarioMarcaLidoMangaDao = jdbi.onDemand(UsuarioMarcaLidoMangaDao.class);
    }

    public UsuarioMarcaLidoManga inserir(UsuarioMarcaLidoManga umcm) {   	
        Long id = usuarioMarcaLidoMangaDao.inserir(umcm);
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao gerar ID para a marcação de lido.");
        }

        return umcm;
    }
    
    public UsuarioMarcaLidoManga excluir(Long idUsuario, Long idManga) {
    	UsuarioMarcaLidoManga uAux = usuarioMarcaLidoMangaDao.consultarPorId(idUsuario, idManga);
        if (uAux == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Marcação de lido não encontrado.");
        }

        Integer qtd = usuarioMarcaLidoMangaDao.excluir(idUsuario, idManga);

        if (qtd != 1) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A quantidade de entidades excluídas é " + qtd + ".");
        }

        return uAux;
    }
}
