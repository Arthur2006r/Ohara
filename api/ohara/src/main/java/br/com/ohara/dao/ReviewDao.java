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
	@SqlUpdate("INSERT INTO Review (idUsuario, idManga, descricao) "
			+ "VALUES (:usuario.idUsuario, :idManga, :descricao)")
	Long inserir(@BindBean Review usuarioAvaliaManga);

	@SqlQuery("SELECT r.idReview AS r_idReview, r.idManga AS r_idManga, r.idUsuario AS r_idUsuario, "
			+ "       r.descricao AS r_descricao, "
			+ "       u.idUsuario AS u_idUsuario, u.nome AS u_nome, u.email AS u_email " + "FROM Review r "
			+ "JOIN Usuario u ON r.idUsuario = u.idUsuario " + "WHERE r.idReview = :idReview")
	@RegisterBeanMapper(value = Review.class, prefix = "r")
	@RegisterBeanMapper(value = Usuario.class, prefix = "u")
	Review consultarPorId(@Bind("idReview") Long idReview);

	@SqlQuery("SELECT r.idReview AS r_idReview, r.idManga AS r_idManga, r.idUsuario AS r_idUsuario, "
			+ "       r.descricao AS r_descricao, "
			+ "       u.idUsuario AS u_idUsuario, u.nome AS u_nome, u.email AS u_email " + "FROM Review r "
			+ "JOIN Usuario u ON r.idUsuario = u.idUsuario")
	@RegisterBeanMapper(value = Review.class, prefix = "r")
	@RegisterBeanMapper(value = Usuario.class, prefix = "u")
	List<Review> consultarTodos();

	@SqlQuery("SELECT r.idReview AS r_idReview, r.idManga AS r_idManga, r.idUsuario AS r_idUsuario, "
			+ "       r.descricao AS r_descricao, "
			+ "       u.idUsuario AS u_idUsuario, u.nome AS u_nome, u.email AS u_email " + "FROM Review r "
			+ "JOIN Usuario u ON r.idUsuario = u.idUsuario " + "WHERE r.idManga = :idManga")
	@RegisterBeanMapper(value = Review.class, prefix = "r")
	@RegisterBeanMapper(value = Usuario.class, prefix = "u")
	List<Review> consultarTodosManga(@Bind("idManga") Long idManga);

	@SqlQuery("SELECT r.idReview AS r_idReview, r.idManga AS r_idManga, r.idUsuario AS r_idUsuario, "
			+ "       r.descricao AS r_descricao, "
			+ "       u.idUsuario AS u_idUsuario, u.nome AS u_nome, u.email AS u_email " + "FROM Review r "
			+ "JOIN Usuario u ON r.idUsuario = u.idUsuario "
			+ "WHERE r.idManga = :idManga AND r.idUsuario = :idUsuario")
	@RegisterBeanMapper(value = Review.class, prefix = "r")
	@RegisterBeanMapper(value = Usuario.class, prefix = "u")
	List<Review> consultarSeguidosManga(@Bind("idManga") Long idManga, @Bind("idUsuario") Long idUsuario);

	@SqlQuery("SELECT r.idReview AS r_idReview, r.idManga AS r_idManga, r.idUsuario AS r_idUsuario, "
			+ "       r.descricao AS r_descricao, "
			+ "       u.idUsuario AS u_idUsuario, u.nome AS u_nome, u.email AS u_email " + "FROM Review r "
			+ "JOIN Usuario u ON r.idUsuario = u.idUsuario "
			+ "WHERE r.idManga = :idManga AND r.idUsuario = :idUsuario")
	@RegisterBeanMapper(value = Review.class, prefix = "r")
	@RegisterBeanMapper(value = Usuario.class, prefix = "u")
	List<Review> consultarMeusManga(@Bind("idManga") Long idManga, @Bind("idUsuario") Long idUsuario);

	@SqlUpdate("DELETE FROM Review WHERE idReview = :idReview")
	Integer excluir(@Bind("idReview") Long idReview);
}
