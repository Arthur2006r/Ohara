package br.com.ohara.dao;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import br.com.ohara.model.Usuario;
import br.com.ohara.model.UsuarioAvaliaManga;

@RegisterBeanMapper(UsuarioAvaliaManga.class)
public interface UsuarioAvaliaMangaDao {
	
	@GetGeneratedKeys
    @SqlUpdate("INSERT INTO UsuarioAvaliaManga (idUsuario, idManga, nota) " +
               "VALUES (:idUsuario, :idManga, :nota)")
    Long inserir(@BindBean UsuarioAvaliaManga usuarioAvaliaManga);

	@SqlQuery("SELECT * FROM UsuarioAvaliaManga WHERE idUsuario = :idUsuario and idManga = :idManga")
	UsuarioAvaliaManga consultarPorId(@Bind("idUsuario") Long idUsuario, @Bind("idManga") Long idManga);

	@SqlUpdate("UPDATE UsuarioAvaliaManga SET nota = :nota"
			+ "WHERE idUsuario = :idUsuario and idManga = :idManga")
	Integer alterar(@BindBean UsuarioAvaliaManga usuarioAvaliaManga);

	@SqlUpdate("DELETE FROM UsuarioAvaliaManga WHERE idUsuario = :idUsuario and idManga = :idManga")
	Integer excluir(@Bind("idUsuario") Long idUsuario, @Bind("idManga") Long idManga);
}
