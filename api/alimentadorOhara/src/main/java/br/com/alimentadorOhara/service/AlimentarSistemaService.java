package br.com.alimentadorOhara.service;

import java.util.List;
import java.util.ArrayList;

import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

import br.com.alimentadorOhara.dao.MangaDao;
import br.com.alimentadorOhara.model.Manga;

@Service
public class AlimentarSistemaService {
    
    private final MangaDao mangaDao;
    
    public AlimentarSistemaService(Jdbi jdbi) {
        this.mangaDao = jdbi.onDemand(MangaDao.class);
    }

    public List<Manga> inserirMangas(List<Manga> mangas) {
        List<Manga> mangasInseridos = new ArrayList<>();
        for (Manga manga : mangas) {
        	System.out.println(manga);
            mangaDao.inserir(manga);
        }
        return mangasInseridos;
    }
    
    public List<Manga> consultarTodos() {
		return mangaDao.consultarTodos();
	}
}
