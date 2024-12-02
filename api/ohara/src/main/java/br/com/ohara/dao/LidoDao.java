package br.com.ohara.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

<<<<<<< HEAD
=======
import br.com.ohara.model.Curtida;
import br.com.ohara.model.LerDepois;
>>>>>>> cb8dfac481dca3130eeacb510eac0a915d5b5818
import br.com.ohara.model.Lido;

@RegisterBeanMapper(Lido.class)
public interface LidoDao {

	@SqlUpdate("INSERT INTO Lido (idUsuario, idManga) " + "VALUES (:idUsuario, :idManga)")
	int inserir(@BindBean Lido usuarioAvaliaManga);

	@SqlQuery("SELECT * FROM Lido WHERE idUsuario = :idUsuario and idManga = :idManga")
	Lido consultarPorId(@Bind("idUsuario") Long idUsuario, @Bind("idManga") Long idManga);

	@SqlQuery("SELECT * FROM Lido")
	List<Lido> consultarTodos();

	@SqlQuery("SELECT * FROM Lido WHERE idManga = :idManga AND idUsuario = :idUsuario")
	Lido consultarMeuLido(@Bind("idManga") Long idManga, @Bind("idUsuario") Long idUsuario);

	@SqlUpdate("DELETE FROM Lido WHERE idUsuario = :idUsuario and idManga = :idManga")
	Integer excluir(@Bind("idUsuario") Long idUsuario, @Bind("idManga") Long idManga);
	
	@SqlQuery("SELECT * FROM Lido WHERE idUsuario = :idUsuario")
	@RegisterBeanMapper(Lido.class)
	List<Lido>consultarPorUsuario(@Bind("idUsuario") Long idUsuario);
}
