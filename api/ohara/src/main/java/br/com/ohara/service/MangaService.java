package br.com.ohara.service;

import java.util.List;

import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

import br.com.ohara.dao.MangaDao;
import br.com.ohara.model.Manga;

@Service
public class MangaService {
	private final MangaDao mangaDao;

	public MangaService(Jdbi jdbi) {
		this.mangaDao = jdbi.onDemand(MangaDao.class);
	}

	public List<Manga> consultarTodos() {
		return mangaDao.consultarTodos();
	}
	
	public List<Manga> consultarTop10() {
		return mangaDao.consultarTop10();
	}
	
	public List<Manga> consultarCurtidosUsuario(Long idUsuario) {
		return mangaDao.consultarCurtidosUsuario(idUsuario);
	}
	
	public List<Manga> consultarLerDepoisUsuario(Long idUsuario) {
		return mangaDao.consultarLerDepoisUsuario(idUsuario);
	}

	public Manga consultarPorId(Long id) {
		return mangaDao.consultarPorId(id);
	}
}
