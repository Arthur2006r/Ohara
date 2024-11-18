package br.com.ohara.service;

import java.util.List;

import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

import br.com.ohara.dao.MangaDao;
import br.com.ohara.model.Manga;
import br.com.ohara.model.filter.MangaFilter;
import io.micrometer.common.util.StringUtils;

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

	public List<Manga> consultarPorFiltro(MangaFilter mangaFilter) {
		// If actionsite is not null and empty AuctionSite field = null.
		if (mangaFilter != null) {
			if (StringUtils.isBlank(mangaFilter.getTitulo())) {
				mangaFilter.setTitulo(null);
			} else {
				mangaFilter.setTitulo("%" + mangaFilter.getTitulo() + "%");
			}

			if (StringUtils.isBlank(mangaFilter.getAutor())) {
				mangaFilter.setAutor(null);
			} else {
				mangaFilter.setAutor("%" + mangaFilter.getAutor() + "%");
			}

			if (StringUtils.isBlank(mangaFilter.getAnoDePublicacao())) {
				mangaFilter.setAnoDePublicacao(null);
			} else {
				mangaFilter.setAnoDePublicacao("%" + mangaFilter.getAnoDePublicacao() + "%");
			}
			
			if (StringUtils.isBlank(mangaFilter.getStatus())) {
				mangaFilter.setStatus(null);
			} else {
				mangaFilter.setStatus(mangaFilter.getStatus());
			}

		} else {
			mangaFilter = new MangaFilter();
		}

		return mangaDao.getByFilter(mangaFilter.getTitulo(), mangaFilter.getAutor(), mangaFilter.getAnoDePublicacao(),
				mangaFilter.getStatus());
	}
}
