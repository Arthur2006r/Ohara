package br.com.ohara.service;

import java.util.List;

import org.jdbi.v3.core.Jdbi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.ohara.dao.AvaliacaoDao;
import br.com.ohara.model.Avaliacao;
import br.com.ohara.model.Review;

@Service
public class AvaliacaoService {
	private final AvaliacaoDao avaliacaoDao;

    public AvaliacaoService(Jdbi jdbi) {
        this.avaliacaoDao = jdbi.onDemand(AvaliacaoDao.class);
    }

    public Avaliacao inserir(Avaliacao u) {
        Long id = avaliacaoDao.inserir(u);
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao gerar ID para a nova avaliação.");
        }

        return u;
    }

    public Avaliacao alterar(Avaliacao uam) {
        Long idManga = uam.getIdManga();
        if (idManga == null) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "id Mangá é informação obrigatória.");
        }
        
        Long idUsuario = uam.getIdUsuario();
        if (idUsuario == null) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "id Usuário é informação obrigatória.");
        }

        Avaliacao uAux = avaliacaoDao.consultarPorId(idUsuario, idManga);
        if (uAux == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliação não encontrada.");
        }

        Integer qtd = avaliacaoDao.alterar(uam);
        if (qtd == null || qtd != 1) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A quantidade de entidades alteradas é " + qtd + ".");
        }

        uAux = avaliacaoDao.consultarPorId(idUsuario, idManga);
        return uAux;
    }
    
    public List<Avaliacao> consultarTodos() {
        return avaliacaoDao.consultarTodos();
    }
    
    public List<Avaliacao> consultarTodosManga(Long idManga) {
        return avaliacaoDao.consultarTodosManga(idManga);
    }
	
	public List<Avaliacao> consultarSeguidosManga(Long idManga, Long idUsuario) {
        return avaliacaoDao.consultarSeguidosManga(idManga, idUsuario);
    }
	
	public List<Avaliacao> consultarMinha(Long idManga, Long idUsuario) {
        return avaliacaoDao.consultarMinha(idManga, idUsuario);
    }

    public Avaliacao excluir(Long idUsuario, Long idManga) {
    	Avaliacao uAux = avaliacaoDao.consultarPorId(idUsuario, idManga);
        if (uAux == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliação não encontrada.");
        }

        Integer qtd = avaliacaoDao.excluir(idUsuario, idManga);

        if (qtd != 1) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A quantidade de entidades excluídas é " + qtd + ".");
        }

        return uAux;
    }
}
