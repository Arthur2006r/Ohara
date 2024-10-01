package br.com.ohara.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import br.com.ohara.model.ListaManga;

@RegisterBeanMapper(ListaManga.class)
public interface ListaMangaDao {

	@GetGeneratedKeys
	@SqlUpdate("INSERT INTO ListaManga (idUsuario, idManga) " + "VALUES (:idUsuario, :idManga)")
	Long inserir(@BindBean ListaManga usuarioAvaliaManga);

	@SqlQuery("SELECT * FROM ListaManga WHERE idListaManga = :idListaManga")
	ListaManga consultarPorId(@Bind("idListaManga") Long idListaManga);

	@SqlQuery("SELECT * FROM ListaManga WHERE idLista = :idLista")
	List<ListaManga> consultarPorLista(@Bind("idLista") Long idLista);

	@SqlUpdate("DELETE FROM ListaManga WHERE idListaManga = :idListaManga")
	Integer excluir(@Bind("idListaManga") Long idListaManga);
}
