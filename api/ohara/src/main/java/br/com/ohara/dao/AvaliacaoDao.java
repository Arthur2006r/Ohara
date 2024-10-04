package br.com.ohara.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import br.com.ohara.model.Usuario;
import br.com.ohara.model.Avaliacao;
import br.com.ohara.model.Review;

@RegisterBeanMapper(Avaliacao.class)
public interface AvaliacaoDao {
	
	@GetGeneratedKeys
    @SqlUpdate("INSERT INTO Avaliacao (idUsuario, idManga, nota) " +
               "VALUES (:idUsuario, :idManga, :nota)")
    Long inserir(@BindBean Avaliacao usuarioAvaliaManga);

	@SqlQuery("SELECT * FROM Avaliacao WHERE idUsuario = :idUsuario and idManga = :idManga")
	Avaliacao consultarPorId(@Bind("idUsuario") Long idUsuario, @Bind("idManga") Long idManga);

	@SqlQuery("SELECT * FROM Avaliacao")
	List<Avaliacao> consultarTodos();
	
	@SqlQuery("SELECT * FROM Avaliacao WHERE idManga = :idManga")
	List<Avaliacao> consultarTodosManga(@Bind("idManga") Long idManga);
	
	// !!! consertar quando for implementar a associação de seguir usuários !!!!
	@SqlQuery("SELECT * FROM Avaliacao WHERE idManga = :idManga AND idUsuario = :idUsuario")
	List<Avaliacao> consultarSeguidosManga(@Bind("idManga") Long idManga, @Bind("idUsuario") Long idUsuario);
	
	@SqlQuery("SELECT * FROM Avaliacao WHERE idManga = :idManga AND idUsuario = :idUsuario")
	List<Avaliacao> consultarMinha(@Bind("idManga") Long idManga, @Bind("idUsuario") Long idUsuario);
	
	@SqlUpdate("UPDATE Avaliacao SET nota = :nota"
			+ "WHERE idUsuario = :idUsuario and idManga = :idManga")
	Integer alterar(@BindBean Avaliacao usuarioAvaliaManga);

	@SqlUpdate("DELETE FROM Avaliacao WHERE idUsuario = :idUsuario and idManga = :idManga")
	Integer excluir(@Bind("idUsuario") Long idUsuario, @Bind("idManga") Long idManga);
}
