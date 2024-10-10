package br.com.ohara.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import br.com.ohara.model.Curtida;
import br.com.ohara.model.LerDepois;
import br.com.ohara.model.Usuario;

@RegisterBeanMapper(LerDepois.class)
public interface LerDepoisDao {

    @SqlUpdate("INSERT INTO LerDepois (idUsuario, idManga) " +
               "VALUES (:idUsuario, :idManga)")
    int inserir(@BindBean LerDepois usuarioAvaliaManga);

	@SqlQuery("SELECT * FROM LerDepois WHERE idUsuario = :idUsuario and idManga = :idManga")
	LerDepois consultarPorId(@Bind("idUsuario") Long idUsuario, @Bind("idManga") Long idManga);

	@SqlQuery("SELECT * FROM LerDepois")
	List<LerDepois> consultarTodos();
	
	@SqlQuery("SELECT * FROM LerDepois WHERE idManga = :idManga AND idUsuario = :idUsuario")
	LerDepois consultarMinhaCurtida(@Bind("idManga") Long idManga, @Bind("idUsuario") Long idUsuario);

	@SqlUpdate("DELETE FROM LerDepois WHERE idManga = :idManga and idManga = :idManga")
	Integer excluir(@Bind("idUsuario") Long idUsuario, @Bind("idManga") Long idManga);
}
