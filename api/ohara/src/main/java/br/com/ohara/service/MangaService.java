package br.com.ohara.service;

import java.util.List;

import org.jdbi.v3.core.Jdbi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.ohara.dao.MangaDao;
import br.com.ohara.model.Manga;

@Service
public class MangaService {
	private final MangaDao mangaDao;
	
	public MangaService(Jdbi jdbi) {
		this.mangaDao = jdbi.onDemand(MangaDao.class);
	}
	
	public Manga inserir(Manga m) {
		if (m.getIdManga() != null) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Id - informacao ilegal.");
        }

        Long id = mangaDao.insert(m);
        m.setIdManga(id);
        return m;
	}
	
	public List<Manga> consultarTodos() {
		return mangaDao.getAll();
	}
	
	public Manga consultarPorId(Long id) {
		return mangaDao.get(id);
	}
	
	public Manga alterar(Manga m) {
        //Validacoes extras das informacoes
        Long id = m.getIdManga();
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Id eh informacao obrigatoria.");
        }
        
        Manga mAux = mangaDao.get(id);
        if (mAux == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Manga nao encontrado com o id: " +id+ ".");
        }
        
        //Alteracao da entidade
        Integer qtd = mangaDao.update(m);
        
        //Validar se a entidade foi alterada corretamente.
        if (qtd == null || qtd != 1){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A quantidade de entidades alteradas eh " +qtd+ ".");
        }
        
        //Retornar a informacao alterada no banco de dados.
        mAux = mangaDao.get(id);
        return mAux;
	}
	
	public Manga excluir(Long id) {
		//Validacoes extras das informacoes
        Manga mAux = mangaDao.get(id);
        if (mAux == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Manga nao encontrado com o id: " +id+ ".");
        }
        
        //Alteracao da entidade
        Long qtd = mangaDao.delete(id);
        
        //Validar se a entidade foi alterada corretamente.
        if (qtd != 1){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A quantidade de entidades alteradas eh " +qtd+ ".");
        }
        
        return mAux;
	}
}
