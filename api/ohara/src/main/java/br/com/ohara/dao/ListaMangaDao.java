package br.com.ohara.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import br.com.ohara.model.ListaManga;
import br.com.ohara.model.Manga;

@RegisterBeanMapper(ListaManga.class)
public interface ListaMangaDao {

	@GetGeneratedKeys
	@SqlUpdate("INSERT INTO ListaManga (idUsuario, idManga) " + "VALUES (:idUsuario, :idManga)")
	Long inserir(@BindBean ListaManga usuarioAvaliaManga);

	@SqlQuery("SELECT * FROM ListaManga WHERE idLista = :idLista AND idManga = :idManga")
	ListaManga consultarPorId(@Bind("idLista") Long idLista, @Bind("idManga") Long idManga);

	@SqlUpdate("DELETE FROM ListaManga WHERE idLista = :idLista AND idManga = :idManga")
	Integer excluir(@Bind("idLista") Long idLista, @Bind("idManga") Long idManga);

	@SqlQuery("""
			    SELECT
			        m.idManga,
			        m.titulo,
			        m.autor,
			        m.sinopse,
			        m.capa,
			        m.anoDePublicacao,
			        m.qtdDeCapitulos,
			        m.qtdDeVolumes,
			        m.popularidade,
			        m.status
			    FROM
			        ListaManga lm
			    JOIN
			        Manga m ON lm.idManga = m.idManga
			    WHERE
			        lm.idLista = :idLista
			""")
	@RegisterConstructorMapper(Manga.class)
	List<Manga> consultarMangasLista(@Bind("idLista") Long idLista);
}
