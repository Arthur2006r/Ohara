package br.com.ohara.service;

import java.util.List;

import org.jdbi.v3.core.Jdbi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.ohara.dao.LidoDao;
import br.com.ohara.model.Curtida;
import br.com.ohara.model.Lido;

@Service
public class LidoService {
	private final LidoDao lidoDao;

    public LidoService(Jdbi jdbi) {
        this.lidoDao = jdbi.onDemand(LidoDao.class);
    }

    public Lido inserir(Lido l) {   	
        Long id = lidoDao.inserir(l);
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao gerar ID para a marcação de lido.");
        }

        return l;
    }
    
    public List<Lido> consultarTodos() {
        return lidoDao.consultarTodos();
    }
    
    public Lido consultarMeuLido(Long idManga, Long idUsuario) {
    	Lido v = lidoDao.consultarMeuLido(idManga, idUsuario);
        if (v == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lido não encontrada.");
        }
        return v;
    }
    
    public Lido excluir(Long idUsuario, Long idManga) {
    	Lido uAux = lidoDao.consultarPorId(idUsuario, idManga);
        if (uAux == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Marcação de lido não encontrado.");
        }

        Integer qtd = lidoDao.excluir(idUsuario, idManga);

        if (qtd != 1) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A quantidade de entidades excluídas é " + qtd + ".");
        }

        return uAux;
    }
}
