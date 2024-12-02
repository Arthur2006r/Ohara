package br.com.ohara.service;

import java.util.List;

import org.jdbi.v3.core.Jdbi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.ohara.dao.AvaliacaoDao;
import br.com.ohara.model.Avaliacao;

@Service
public class AvaliacaoService {
	private final AvaliacaoDao avaliacaoDao;
	private final UsuarioService usuarioService;

	public AvaliacaoService(Jdbi jdbi, UsuarioService usuarioService) {
		this.avaliacaoDao = jdbi.onDemand(AvaliacaoDao.class);
		this.usuarioService = usuarioService;
	}

	public Avaliacao inserir(Avaliacao u) {
		int linhasAfetadas = avaliacaoDao.inserir(u);
		if (linhasAfetadas <= 0) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao inserir a nova avaliação.");
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
			throw new ResponseStatusException(HttpStatus.CONFLICT,
					"A quantidade de entidades alteradas é " + qtd + ".");
		}

		uAux = avaliacaoDao.consultarPorId(idUsuario, idManga);
		return uAux;
	}

	public List<Avaliacao> consultarTodos() {
		List<Avaliacao> avaliacoes = avaliacaoDao.consultarTodos();
		for (Avaliacao avaliacao : avaliacoes) {
			avaliacao.setUsuario(this.usuarioService.consultarPorId(avaliacao.getIdUsuario()));
		}
		return avaliacoes;
	}

	public List<Avaliacao> consultarTodosManga(Long idManga) {
		List<Avaliacao> avaliacoes = avaliacaoDao.consultarTodosManga(idManga);
		for (Avaliacao avaliacao : avaliacoes) {
			avaliacao.setUsuario(this.usuarioService.consultarPorId(avaliacao.getIdUsuario()));
		}
		return avaliacoes;
	}

	public List<Avaliacao> consultarSeguidosManga(Long idManga, Long idUsuario) {
		List<Avaliacao> avaliacoes = avaliacaoDao.consultarSeguidosManga(idManga, idUsuario);
		for (Avaliacao avaliacao : avaliacoes) {
			avaliacao.setUsuario(this.usuarioService.consultarPorId(avaliacao.getIdUsuario()));
		}
		return avaliacoes;
	}

	public Avaliacao consultarMinha(Long idManga, Long idUsuario) {
		Avaliacao avaliacao = avaliacaoDao.consultarMinha(idManga, idUsuario);
		if (avaliacao != null) {
			avaliacao.setUsuario(this.usuarioService.consultarPorId(avaliacao.getIdUsuario()));
		}
		return avaliacao;
	}

	public Avaliacao excluir(Long idUsuario, Long idManga) {
		Avaliacao uAux = avaliacaoDao.consultarPorId(idUsuario, idManga);
		if (uAux == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliação não encontrada.");
		}

		Integer qtd = avaliacaoDao.excluir(idUsuario, idManga);

		if (qtd != 1) {
			throw new ResponseStatusException(HttpStatus.CONFLICT,
					"A quantidade de entidades excluídas é " + qtd + ".");
		}

		return uAux;
	}
}
