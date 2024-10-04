package br.com.ohara.service;

import java.util.List;

import org.jdbi.v3.core.Jdbi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.ohara.dao.CurtidaDao;
import br.com.ohara.model.Curtida;
import br.com.ohara.model.LerDepois;
import br.com.ohara.model.Usuario;

@Service
public class CurtidaService {
	private final CurtidaDao curtidaDao;

	public CurtidaService(Jdbi jdbi) {
		this.curtidaDao = jdbi.onDemand(CurtidaDao.class);
	}

	public Curtida inserir(Curtida c) {
		int linhasAfetadas = curtidaDao.inserir(c);
		if (linhasAfetadas <= 0) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao inserir a nova curtida.");
		}

		return c;
	}

	public List<Curtida> consultarTodos() {
		return curtidaDao.consultarTodos();
	}

	public Curtida consultarMinhaCurtida(Long idManga, Long idUsuario) {
		Curtida c = curtidaDao.consultarMinhaCurtida(idManga, idUsuario);
		if (c == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Curtida não encontrada.");
		}
		return c;
	}

	public Curtida excluir(Long idUsuario, Long idManga) {
		Curtida uAux = curtidaDao.consultarPorId(idUsuario, idManga);
		if (uAux == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Curtida não encontrada.");
		}

		Integer qtd = curtidaDao.excluir(idUsuario, idManga);

		if (qtd != 1) {
			throw new ResponseStatusException(HttpStatus.CONFLICT,
					"A quantidade de entidades excluídas é " + qtd + ".");
		}

		return uAux;
	}
}
