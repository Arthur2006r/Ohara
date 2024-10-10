package br.com.alimentadorOhara.service;

import java.util.ArrayList;
import java.util.List;

import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

import br.com.alimentadorOhara.dao.MangaDao;
import br.com.alimentadorOhara.model.Manga;

@Service
public class AlimentadorOharaService {
	private final MangaDao mangaDao;

	public AlimentadorOharaService(Jdbi jdbi) {
		this.mangaDao = jdbi.onDemand(MangaDao.class);
	}

	public List<Manga> inserirMangas(List<Manga> mangas) {
		List<Manga> mangasInseridos = new ArrayList<>();
		for (Manga manga : mangas) {
			if(manga.getStatus() == null) {
				manga.setStatus("Em hiato");
			}
			
			if (manga.getAnoDePublicacao() == null || manga.getAnoDePublicacao().isEmpty()) {
		        manga.setAnoDePublicacao("0"); 
		    }
			
			int ct = mangaDao.countByTitulo(manga.getTitulo());

			if (ct == 0) {
				mangaDao.inserir(manga);
			}
		}
		return mangasInseridos;
	}
}
