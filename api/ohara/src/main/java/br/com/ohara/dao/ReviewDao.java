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

@RegisterBeanMapper(Review.class)
public interface ReviewDao {
	
	@GetGeneratedKeys
    @SqlUpdate("INSERT INTO UsuarioComentaManga (idUsuario, idManga, descricao) " +
               "VALUES (:idUsuario, :idManga, :descricao)")
    Long inserir(@BindBean Review usuarioAvaliaManga);

	@SqlQuery("SELECT * FROM UsuarioComentaManga WHERE idUsuarioComentaManga = :idUsuarioComentaManga")
	Review consultarPorId(@Bind("idUsuarioComentaManga") Long idUsuarioComentaManga);

	@SqlQuery("SELECT * FROM UsuarioComentaManga")
	List<Review> consultarTodos();
	
	@SqlQuery("SELECT * FROM UsuarioComentaManga WHERE idManga = :idManga")
	List<Review> consultarTodosManga(@Bind("idManga") Long idManga);
	
	// !!! consertar quando for implementar a associação de seguir usuários !!!!
	@SqlQuery("SELECT * FROM UsuarioComentaManga WHERE idManga = :idManga AND idUsuario = :idUsuario")
	List<Review> consultarSeguidosManga(@Bind("idManga") Long idManga, @Bind("idUsuario") Long idUsuario);
	
	@SqlQuery("SELECT * FROM UsuarioComentaManga WHERE idManga = :idManga AND idUsuario = :idUsuario")
	List<Review> consultarMeusManga(@Bind("idManga") Long idManga, @Bind("idUsuario") Long idUsuario);

	@SqlUpdate("DELETE FROM UsuarioComentaManga WHERE idUsuarioComentaManga = :idUsuarioComentaManga")
	Integer excluir(@Bind("idUsuarioComentaManga") Long idUsuarioComentaManga);
}
