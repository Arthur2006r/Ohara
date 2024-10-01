package br.com.ohara.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import br.com.ohara.model.Lista;

@RegisterBeanMapper(Lista.class)
public interface ListaDao {

	@GetGeneratedKeys
	@SqlUpdate("INSERT INTO Lista (idUsuario, titulo)" + "VALUES (:idUsuario, :titulo)")
	Long inserir(@BindBean Lista lista);

	@SqlQuery("SELECT * FROM Lista WHERE idLista = :idLista")
	Lista consultarPorId(@Bind("idLista") Long idLista);

	@SqlQuery("SELECT * FROM Lista WHERE idUsuario = :idUsuario")
	List<Lista> consultarTodosUsuario(@Bind("idUsuario") Long idUsuario);

	@SqlUpdate("UPDATE Lista SET titulo = :titulo "
			+ "WHERE idLista = :idLista")
	Integer alterar(@BindBean Lista lista);

	@SqlUpdate("DELETE FROM Lista WHERE idLista = :idLista")
	Integer excluir(@Bind("idLista") Long idLista);
}
