package br.com.alimentadorOhara.dao;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import br.com.alimentadorOhara.model.Manga;

@RegisterBeanMapper(Manga.class)
public interface MangaDao {

	@GetGeneratedKeys
	@SqlUpdate("insert into manga (titulo, sinopse, autor, qtdDeVolumes, qtdDeCapitulos) values (:titulo, :sinopse, :autor, :qtdDeVolumes, :qtdDeCapitulos)")
	Long inserir(@BindBean Manga manga);
}
