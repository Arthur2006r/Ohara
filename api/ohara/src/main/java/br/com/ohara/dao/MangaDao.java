package br.com.ohara.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import br.com.ohara.model.Manga;

@RegisterBeanMapper(Manga.class)
public interface MangaDao {

	@SqlQuery("select *" + " from Manga " + " order by titulo;")
	List<Manga> consultarTodos();
	
	@SqlQuery("SELECT * FROM Manga LIMIT 10")
	List<Manga> consultarTop10();

	// !!! consertar logica !!!!
	@SqlQuery("select *" + " from Manga " + " order by titulo;")
	List<Manga> consultarCurtidosUsuario(@Bind("idUsuario") Long idUsuario);

	// !!! consertar logica !!!!
	@SqlQuery("select *" + " from Manga " + " order by titulo;")
	List<Manga> consultarLerDepoisUsuario(@Bind("idUsuario") Long idUsuario);

	@SqlQuery("select *" + " from Manga " + " where idManga = :idManga;")
	Manga consultarPorId(@Bind("idManga") Long id);
}
