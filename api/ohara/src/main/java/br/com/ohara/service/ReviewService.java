package br.com.ohara.service;

import java.util.List;

import org.jdbi.v3.core.Jdbi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.ohara.dao.ReviewDao;
import br.com.ohara.model.Review;

@Service
public class ReviewService {
	private final ReviewDao reviewDao;
	private final UsuarioService usuarioService;

	public ReviewService(Jdbi jdbi, UsuarioService usuarioService) {
		this.reviewDao = jdbi.onDemand(ReviewDao.class);
		this.usuarioService = usuarioService;
	}

	public Review inserir(Review r) {
		if (r.getIdReview() != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"ID deve ser nulo ao inserir um novo comentário.");
		}

		Long id = reviewDao.inserir(r);
		if (id == null) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Erro ao gerar ID para o novo comentário.");
		}

		return r;
	}

	public Review consultarPorId(Long id) {
		Review review = reviewDao.consultarPorId(id);
		if (review == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review não encontrada com o id: " + id + ".");
		}

		review.setUsuario(this.usuarioService.consultarPorId(review.getIdUsuario()));
		return review;
	}

	public List<Review> consultarTodos() {
		List<Review> reviews = reviewDao.consultarTodos();
		for (Review review : reviews) {
			review.setUsuario(this.usuarioService.consultarPorId(review.getIdUsuario()));
		}
		return reviews;
	}

	public List<Review> consultarTodosManga(Long idManga) {
		List<Review> reviews = reviewDao.consultarTodosManga(idManga);
		for (Review review : reviews) {
			review.setUsuario(this.usuarioService.consultarPorId(review.getIdUsuario()));
		}
		return reviews;
	}

	public List<Review> consultarSeguidosManga(Long idManga, Long idUsuario) {
		List<Review> reviews = reviewDao.consultarSeguidosManga(idManga, idUsuario);
		for (Review review : reviews) {
			review.setUsuario(this.usuarioService.consultarPorId(review.getIdUsuario()));
		}
		return reviews;
	}

	public List<Review> consultarMeusManga(Long idManga, Long idUsuario) {
		List<Review> reviews = reviewDao.consultarMeusManga(idManga, idUsuario);
		for (Review review : reviews) {
			review.setUsuario(this.usuarioService.consultarPorId(review.getIdUsuario()));
		}
		return reviews;
	}

	public Review excluir(Long idUsuarioComentaManga) {
		Review uAux = reviewDao.consultarPorId(idUsuarioComentaManga);
		if (uAux == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comentário não encontrada.");
		}

		Integer qtd = reviewDao.excluir(idUsuarioComentaManga);

		if (qtd != 1) {
			throw new ResponseStatusException(HttpStatus.CONFLICT,
					"A quantidade de entidades excluídas é " + qtd + ".");
		}

		return uAux;
	}
}
