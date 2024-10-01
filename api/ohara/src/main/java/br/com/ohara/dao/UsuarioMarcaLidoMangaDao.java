package br.com.ohara.dao;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import br.com.ohara.model.UsuarioMarcaLidoManga;

@RegisterBeanMapper(UsuarioMarcaLidoManga.class)
public interface UsuarioMarcaLidoMangaDao {
	@GetGeneratedKeys
    @SqlUpdate("INSERT INTO UsuarioMarcaLidoManga (idUsuario, idManga) " +
               "VALUES (:idUsuario, :idManga)")
    Long inserir(@BindBean UsuarioMarcaLidoManga usuarioAvaliaManga);

	@SqlQuery("SELECT * FROM UsuarioMarcaLidoManga WHERE idUsuario = :idUsuario and idManga = :idManga")
	UsuarioMarcaLidoManga consultarPorId(@Bind("idUsuario") Long idUsuario, @Bind("idManga") Long idManga);


	@SqlUpdate("DELETE FROM UsuarioMarcaLidoManga WHERE idUsuarioComentaManga = :idUsuarioComentaManga and idManga = :idManga")
	Integer excluir(@Bind("idUsuario") Long idUsuario, @Bind("idManga") Long idManga);
}
