package br.com.alimentadorOhara.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import br.com.alimentadorOhara.model.Manga;

@RegisterBeanMapper(Manga.class)
public interface MangaDao {

	@GetGeneratedKeys
	@SqlUpdate("INSERT INTO Manga (idManga, titulo, sinopse, autor, qtdDeVolumes, qtdDeCapitulos, capa, anoDePublicacao, popularidade, status) " +
	           "VALUES (:idManga, :titulo, :sinopse, :autor, :qtdDeVolumes, :qtdDeCapitulos, :capa, :anoDePublicacao, :popularidade, :status)")
	Long inserir(@BindBean Manga manga);

	
	@SqlQuery("select *" + " from manga " + " order by titulo;")
	List<Manga> consultarTodos();
}
