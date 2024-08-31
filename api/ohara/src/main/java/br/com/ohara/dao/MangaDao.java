package br.com.ohara.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import br.com.ohara.model.Manga;

@RegisterBeanMapper(Manga.class)
public interface MangaDao {

	@GetGeneratedKeys
	@SqlUpdate("insert into manga (titulo, sinopse, autor, quantidadeVolumes, quantidadeCapitulos) values (:titulo, :sinopse, :autor, :quantidadeVolumes, :quantidadeVolumes)")
	Long inserir(@BindBean Manga manga);

	@SqlQuery("select *" + " from manga " + " where idManga = :idManga;")
	Manga consultarPorId(@Bind("idManga") Long id);

	@SqlQuery("select *" + " from manga " + " order by titulo;")
	List<Manga> consultarTodos();

	@SqlUpdate("update manga " + " set titulo = :titulo, " + " sinopse = :sinopse, " + " autor = :autor "
			+ " quantidadeVolumes = :quantidadeVolumes" + " quantidadeCapitulos = :quantidadeCapitulos"
			+ " where idManga = :idManga;")
	Integer alterar(@BindBean Manga manga);

	@SqlUpdate("delete " + " from manga " + " where idManga = :idManga;")
	Long excluir(@Bind("idManga") Long id);
}