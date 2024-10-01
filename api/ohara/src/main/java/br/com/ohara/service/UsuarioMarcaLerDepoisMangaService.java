package br.com.ohara.service;

import org.jdbi.v3.core.Jdbi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.ohara.dao.UsuarioMarcaLerDepoisMangaDao;
import br.com.ohara.model.UsuarioMarcaLerDepoisManga;
import br.com.ohara.model.UsuarioMarcaLidoManga;

@Service
public class UsuarioMarcaLerDepoisMangaService {
	private final UsuarioMarcaLerDepoisMangaDao usuarioMarcaLerDepoisMangaDao;

    public UsuarioMarcaLerDepoisMangaService(Jdbi jdbi) {
        this.usuarioMarcaLerDepoisMangaDao = jdbi.onDemand(UsuarioMarcaLerDepoisMangaDao.class);
    }

    public UsuarioMarcaLerDepoisManga inserir(UsuarioMarcaLerDepoisManga umldm) {   	
        Long id = usuarioMarcaLerDepoisMangaDao.inserir(umldm);
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao gerar ID para a marcação de ler depois.");
        }

        return umldm;
    }
    
    public UsuarioMarcaLerDepoisManga excluir(Long idUsuario, Long idManga) {
    	UsuarioMarcaLerDepoisManga uAux = usuarioMarcaLerDepoisMangaDao.consultarPorId(idUsuario, idManga);
        if (uAux == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Marcação de ler depois não encontrado.");
        }

        Integer qtd = usuarioMarcaLerDepoisMangaDao.excluir(idUsuario, idManga);

        if (qtd != 1) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A quantidade de entidades excluídas é " + qtd + ".");
        }

        return uAux;
    }
}
