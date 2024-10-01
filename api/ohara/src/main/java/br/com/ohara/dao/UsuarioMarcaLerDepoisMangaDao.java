package br.com.ohara.dao;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import br.com.ohara.model.UsuarioMarcaLerDepoisManga;

@RegisterBeanMapper(UsuarioMarcaLerDepoisMangaDao.class)
public interface UsuarioMarcaLerDepoisMangaDao {
	@GetGeneratedKeys
    @SqlUpdate("INSERT INTO UsuarioMarcaLerDepoisManga (idUsuario, idManga) " +
               "VALUES (:idUsuario, :idManga)")
    Long inserir(@BindBean UsuarioMarcaLerDepoisManga usuarioAvaliaManga);

	@SqlQuery("SELECT * FROM UsuarioMarcaLerDepoisManga WHERE idUsuario = :idUsuario and idManga = :idManga")
	UsuarioMarcaLerDepoisManga consultarPorId(@Bind("idUsuario") Long idUsuario, @Bind("idManga") Long idManga);


	@SqlUpdate("DELETE FROM UsuarioMarcaLerDepoisManga	 WHERE idUsuarioComentaManga = :idUsuarioComentaManga and idManga = :idManga")
	Integer excluir(@Bind("idUsuario") Long idUsuario, @Bind("idManga") Long idManga);
}
