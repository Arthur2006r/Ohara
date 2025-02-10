package br.com.ohara.service;

import java.util.List;

import org.jdbi.v3.core.Jdbi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.ohara.dao.UsuarioSegueUsuarioDao;
import br.com.ohara.model.Review;
import br.com.ohara.model.Usuario;
import br.com.ohara.model.UsuarioSegueUsuario;

@Service
public class UsuarioSegueUsuarioService {
	private final UsuarioSegueUsuarioDao usuarioSegueUsuarioDao;

	public UsuarioSegueUsuarioService(Jdbi jdbi) {
		this.usuarioSegueUsuarioDao = jdbi.onDemand(UsuarioSegueUsuarioDao.class);
	}

	public UsuarioSegueUsuario inserir(UsuarioSegueUsuario usu) {
		Long id = usuarioSegueUsuarioDao.inserir(usu);
		if (id == null) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Erro ao gerar ID para a associação de seguir.");
		}

		return usu;
	}

	public UsuarioSegueUsuario excluir(Long idUsuario, Long idManga) {
		UsuarioSegueUsuario uAux = usuarioSegueUsuarioDao.consultarPorId(idUsuario, idManga);
		if (uAux == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Associação de seguir não encontrada.");
		}

		Integer qtd = usuarioSegueUsuarioDao.excluir(idUsuario, idManga);

		if (qtd != 1) {
			throw new ResponseStatusException(HttpStatus.CONFLICT,
					"A quantidade de entidades excluídas é " + qtd + ".");
		}

		return uAux;
	}

	public List<Usuario> consultarSeguidores(Long idUsuario) {
		List<Usuario> usuarios = usuarioSegueUsuarioDao.consultarSeguidores(idUsuario);

		return usuarios;
	}

	public List<Usuario> consultarSeguidos(Long idUsuario) {
		List<Usuario> usuarios = usuarioSegueUsuarioDao.consultarSeguidos(idUsuario);

		return usuarios;
	}
}
