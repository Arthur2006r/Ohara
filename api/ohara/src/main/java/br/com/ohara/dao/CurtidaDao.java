package br.com.ohara.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import br.com.ohara.model.Curtida;
import br.com.ohara.model.Review;

@RegisterBeanMapper(Review.class)
public interface CurtidaDao {
	@GetGeneratedKeys
    @SqlUpdate("INSERT INTO Curtida (idUsuario, idManga) " +
               "VALUES (:idUsuario, :idManga)")
    Long inserir(@BindBean Curtida usuarioAvaliaManga);

	@SqlQuery("SELECT * FROM Curtida WHERE idUsuario = :idUsuario and idManga = :idManga")
	Curtida consultarPorId(@Bind("idUsuario") Long idUsuario, @Bind("idManga") Long idManga);

	@SqlQuery("SELECT * FROM Curtida")
	List<Curtida> consultarTodos();

	@SqlQuery("SELECT * FROM Curtida WHERE idManga = :idManga AND idUsuario = :idUsuario")
	Curtida consultarMinhaCurtida(@Bind("idManga") Long idManga, @Bind("idUsuario") Long idUsuario);

	@SqlUpdate("DELETE FROM Curtida WHERE idUsuarioComentaManga = :idUsuarioComentaManga and idManga = :idManga")
	Integer excluir(@Bind("idUsuario") Long idUsuario, @Bind("idManga") Long idManga);
}
