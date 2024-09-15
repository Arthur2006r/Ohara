package br.com.ohara.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import br.com.ohara.model.Manga;

@RegisterBeanMapper(Manga.class)
public interface MangaDao {

	@SqlQuery("select *" + " from manga " + " where idManga = :idManga;")
	Manga consultarPorId(@Bind("idManga") Long id);

	@SqlQuery("select *" + " from manga " + " order by titulo;")
	List<Manga> consultarTodos();

}
