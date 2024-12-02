package br.com.ohara.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import br.com.ohara.model.Review;

@RegisterBeanMapper(Review.class)
public interface ReviewDao {
	
	@GetGeneratedKeys
    @SqlUpdate("INSERT INTO Review (idUsuario, idManga, descricao) " +
               "VALUES (:idUsuario, :idManga, :descricao)")
    Long inserir(@BindBean Review usuarioAvaliaManga);

	@SqlQuery("SELECT * FROM Review WHERE idReview = :idReview")
	Review consultarPorId(@Bind("idReview") Long idReview);

	@SqlQuery("SELECT * FROM Review")
	List<Review> consultarTodos();
	
	@SqlQuery("SELECT * FROM Review WHERE idManga = :idManga")
	List<Review> consultarTodosManga(@Bind("idManga") Long idManga);
	
	// !!! consertar quando for implementar a associação de seguir usuários !!!!
	@SqlQuery("SELECT * FROM Review WHERE idManga = :idManga AND idUsuario = :idUsuario")
	List<Review> consultarSeguidosManga(@Bind("idManga") Long idManga, @Bind("idUsuario") Long idUsuario);
	
	@SqlQuery("SELECT * FROM Review WHERE idManga = :idManga AND idUsuario = :idUsuario")
	List<Review> consultarMeusManga(@Bind("idManga") Long idManga, @Bind("idUsuario") Long idUsuario);

	@SqlUpdate("DELETE FROM Review WHERE idReview = :idReview")
	Integer excluir(@Bind("idReview") Long idReview);
}
