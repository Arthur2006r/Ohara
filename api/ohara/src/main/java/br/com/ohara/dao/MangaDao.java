package br.com.ohara.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.AllowUnusedBindings;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.DefineNamedBindings;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.stringtemplate4.UseStringTemplateEngine;

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
	
	@SqlQuery("SELECT AVG(nota) FROM Avaliacao WHERE idManga = :idManga")
	Double calcularNota(@Bind("idManga") Long idManga);

	
	// !! DBF -  FILTRO DE PESQUISA
	@SqlQuery(" SELECT * FROM Manga  \n" +
            " WHERE 1 = 1 \n" +
            " <if(titulo)>  and titulo like :titulo \n<endif>" +
            " <if(autor)>  and autor like :autor \n<endif>" +
            " <if(status)>  and status like :status \n<endif>" +
            " <if(anoDePublicacao)>  and anoDePublicacao = :anoDePublicacao  \n<endif>" +
            " ORDER BY idManga;")
    @AllowUnusedBindings
    @DefineNamedBindings
    @UseStringTemplateEngine
    List<Manga> getByFilter(
            @Bind String titulo,
            @Bind String autor,
            @Bind String anoDePublicacao,
            @Bind String status
    );
}
