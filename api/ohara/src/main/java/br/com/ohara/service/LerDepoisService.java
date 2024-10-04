package br.com.ohara.service;

import java.util.List;

import org.jdbi.v3.core.Jdbi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.ohara.dao.LerDepoisDao;
import br.com.ohara.model.Curtida;
import br.com.ohara.model.LerDepois;
import br.com.ohara.model.Usuario;
import br.com.ohara.model.Lido;

@Service
public class LerDepoisService {
	private final LerDepoisDao lerDepoisDao;

    public LerDepoisService(Jdbi jdbi) {
        this.lerDepoisDao = jdbi.onDemand(LerDepoisDao.class);
    }

    public LerDepois inserir(LerDepois lD) {   	
        int linhasAfetadas = lerDepoisDao.inserir(lD);
        if (linhasAfetadas <= 0) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao inserir a nova curtida.");
		}

        return lD;
    }
    
    public List<LerDepois> consultarTodos() {
        return lerDepoisDao.consultarTodos();
    }
    
    public LerDepois consultarMeuLerDepois(Long idManga, Long idUsuario) {
    	LerDepois lD = lerDepoisDao.consultarMinhaCurtida(idManga, idUsuario);
        if (lD == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ler Depois não encontrado.");
        }
        return lD;
    }
    
    public LerDepois excluir(Long idUsuario, Long idManga) {
    	LerDepois uAux = lerDepoisDao.consultarPorId(idUsuario, idManga);
        if (uAux == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Marcação de ler depois não encontrado.");
        }

        Integer qtd = lerDepoisDao.excluir(idUsuario, idManga);

        if (qtd != 1) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A quantidade de entidades excluídas é " + qtd + ".");
        }

        return uAux;
    }
}
