package br.com.ohara.dao;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import br.com.ohara.model.UsuarioSegueUsuario;

@RegisterBeanMapper(UsuarioSegueUsuario.class)
public interface UsuarioSegueUsuarioDao {
	@GetGeneratedKeys
    @SqlUpdate("INSERT INTO UsuarioSegueUsuario (idUsuario, idManga) " +
               "VALUES (:idUsuario, :idManga)")
    Long inserir(@BindBean UsuarioSegueUsuario usuarioAvaliaManga);

	@SqlQuery("SELECT * FROM UsuarioSegueUsuario WHERE idUsuario = :idUsuario and idManga = :idManga")
	UsuarioSegueUsuario consultarPorId(@Bind("idUsuario") Long idUsuario, @Bind("idManga") Long idManga);


	@SqlUpdate("DELETE FROM UsuarioSegueUsuario WHERE idUsuarioComentaManga = :idUsuarioComentaManga and idManga = :idManga")
	Integer excluir(@Bind("idUsuario") Long idUsuario, @Bind("idManga") Long idManga);
}
