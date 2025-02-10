package br.com.ohara.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import br.com.ohara.model.Review;
import br.com.ohara.model.Usuario;
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

	@SqlQuery("SELECT u.idUsuario, u.nome, u.email " +
	          "FROM UsuarioSegueUsuario us " +
	          "INNER JOIN Usuario u ON us.idUsuarioSegue = u.idUsuario " +
	          "WHERE us.idUsuarioSeguido = :idUsuario")
	List<Usuario> consultarSeguidores(@Bind("idUsuario") Long idUsuario);

	@SqlQuery("SELECT u.idUsuario, u.nome, u.email " +
	          "FROM UsuarioSegueUsuario us " +
	          "INNER JOIN Usuario u ON us.idUsuarioSeguido = u.idUsuario " +
	          "WHERE us.idUsuarioSegue = :idUsuario")
	List<Usuario> consultarSeguidos(@Bind("idUsuario") Long idUsuario);
}
