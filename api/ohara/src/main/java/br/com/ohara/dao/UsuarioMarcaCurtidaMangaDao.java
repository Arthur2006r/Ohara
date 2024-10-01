package br.com.ohara.dao;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import br.com.ohara.model.UsuarioComentaManga;
import br.com.ohara.model.UsuarioMarcaCurtidaManga;

@RegisterBeanMapper(UsuarioComentaManga.class)
public interface UsuarioMarcaCurtidaMangaDao {
	@GetGeneratedKeys
    @SqlUpdate("INSERT INTO UsuarioMarcaCurtidaManga (idUsuario, idManga) " +
               "VALUES (:idUsuario, :idManga)")
    Long inserir(@BindBean UsuarioMarcaCurtidaManga usuarioAvaliaManga);

	@SqlQuery("SELECT * FROM UsuarioMarcaCurtidaManga WHERE idUsuario = :idUsuario and idManga = :idManga")
	UsuarioMarcaCurtidaManga consultarPorId(@Bind("idUsuario") Long idUsuario, @Bind("idManga") Long idManga);


	@SqlUpdate("DELETE FROM UsuarioMarcaCurtidaManga WHERE idUsuarioComentaManga = :idUsuarioComentaManga and idManga = :idManga")
	Integer excluir(@Bind("idUsuario") Long idUsuario, @Bind("idManga") Long idManga);
}
