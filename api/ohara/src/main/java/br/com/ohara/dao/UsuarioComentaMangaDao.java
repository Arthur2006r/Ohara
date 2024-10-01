package br.com.ohara.dao;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import br.com.ohara.model.UsuarioComentaManga;

@RegisterBeanMapper(UsuarioComentaManga.class)
public interface UsuarioComentaMangaDao {
	
	@GetGeneratedKeys
    @SqlUpdate("INSERT INTO UsuarioComentaManga (idUsuario, idManga, descricao) " +
               "VALUES (:idUsuario, :idManga, :descricao)")
    Long inserir(@BindBean UsuarioComentaManga usuarioAvaliaManga);

	@SqlQuery("SELECT * FROM UsuarioComentaManga WHERE idUsuarioComentaManga = :idUsuarioComentaManga")
	UsuarioComentaManga consultarPorId(@Bind("idUsuarioComentaManga") Long idUsuarioComentaManga);


	@SqlUpdate("DELETE FROM UsuarioComentaManga WHERE idUsuarioComentaManga = :idUsuarioComentaManga")
	Integer excluir(@Bind("idUsuarioComentaManga") Long idUsuarioComentaManga);
}
