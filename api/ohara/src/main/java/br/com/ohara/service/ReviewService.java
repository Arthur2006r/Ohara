package br.com.ohara.service;

import java.util.List;

import org.jdbi.v3.core.Jdbi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.ohara.dao.ReviewDao;
import br.com.ohara.model.Review;
import br.com.ohara.model.Usuario;

@Service
public class ReviewService {
	private final ReviewDao reviewDao;

    public ReviewService(Jdbi jdbi) {
        this.reviewDao = jdbi.onDemand(ReviewDao.class);
    }

    public Review inserir(Review r) {
    	if (r.getIdReview() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID deve ser nulo ao inserir um novo comentário.");
        }
    	
        Long id = reviewDao.inserir(r);
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao gerar ID para o novo comentário.");
        }

        return r;
    }
    
    public Review consultarPorId(Long id) {
    	Review review = reviewDao.consultarPorId(id);
        if (review == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review não encontrada com o id: " + id + ".");
        }
        return review;
    }
    
    public List<Review> consultarTodos() {
        return reviewDao.consultarTodos();
    }
    
    public List<Review> consultarTodosManga(Long idManga) {
        return reviewDao.consultarTodosManga(idManga);
    }
	
	public List<Review> consultarSeguidosManga(Long idManga, Long idUsuario) {
        return reviewDao.consultarSeguidosManga(idManga, idUsuario);
    }
	
	public List<Review> consultarMeusManga(Long idManga, Long idUsuario) {
        return reviewDao.consultarMeusManga(idManga, idUsuario);
    }
    
    public Review excluir(Long idUsuarioComentaManga) {
    	Review uAux = reviewDao.consultarPorId(idUsuarioComentaManga);
        if (uAux == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comentário não encontrada.");
        }

        Integer qtd = reviewDao.excluir(idUsuarioComentaManga);

        if (qtd != 1) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A quantidade de entidades excluídas é " + qtd + ".");
        }

        return uAux;
    }
}
