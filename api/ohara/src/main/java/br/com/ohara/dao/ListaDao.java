package br.com.ohara.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import br.com.ohara.model.Lista;

@RegisterBeanMapper(Lista.class)
public interface ListaDao {

	@GetGeneratedKeys
	@SqlUpdate("INSERT INTO Lista (idUsuario, titulo)" + "VALUES (:usuario.idUsuario, :titulo)")
	Long inserir(@BindBean Lista lista);

	@SqlQuery("""
			    SELECT
			        l.idLista AS idLista,
			        l.nome AS titulo,
			        u.idUsuario AS idUsuario,
			        u.nome AS nomeUsuario,
			        u.email AS emailUsuario,
			        u.avatar AS avatarUsuario,
			        m.idManga AS idManga,
			        m.titulo AS tituloManga,
			        m.autor AS autorManga,
			        m.sinopse AS sinopseManga,
			        m.capa AS capaManga,
			        m.anoDePublicacao AS anoPublicacaoManga,
			        m.qtdDeCapitulos AS qtdCapitulosManga,
			        m.qtdDeVolumes AS qtdVolumesManga,
			        m.popularidade AS popularidadeManga,
			        m.status AS statusManga
			    FROM
			        Lista l
			    JOIN
			        Usuario u ON l.idUsuario = u.idUsuario
			    LEFT JOIN
			        ListaManga lm ON l.idLista = lm.idLista
			    LEFT JOIN
			        Manga m ON lm.idManga = m.idManga
			    WHERE
			        l.idLista = :idLista
			""")
	@RegisterConstructorMapper(Lista.class)
	Lista consultarPorId(@Bind("idLista") Long idLista);

	@SqlQuery("""
			    SELECT
			        l.idLista AS idLista,
			        l.nome AS titulo,
			        u.idUsuario AS idUsuario,
			        u.nome AS nomeUsuario,
			        u.email AS emailUsuario,
			        u.avatar AS avatarUsuario,
			        m.idManga AS idManga,
			        m.titulo AS tituloManga,
			        m.autor AS autorManga,
			        m.sinopse AS sinopseManga,
			        m.capa AS capaManga,
			        m.anoDePublicacao AS anoPublicacaoManga,
			        m.qtdDeCapitulos AS qtdCapitulosManga,
			        m.qtdDeVolumes AS qtdVolumesManga,
			        m.popularidade AS popularidadeManga,
			        m.status AS statusManga,
			        u AS usuario, 
			 		m AS mangas 
			    FROM
			        Lista l
			    JOIN
			        Usuario u ON l.idUsuario = u.idUsuario
			    LEFT JOIN
			        ListaManga lm ON l.idLista = lm.idLista
			    LEFT JOIN
			        Manga m ON lm.idManga = m.idManga
			    WHERE
			        l.idUsuario = :idUsuario
			    ORDER BY
			        l.idLista
			""")
	@RegisterConstructorMapper(Lista.class)
	List<Lista> consultarTodosUsuario(@Bind("idUsuario") Long idUsuario);

	@SqlUpdate("UPDATE Lista SET titulo = :titulo " + "WHERE idLista = :idLista")
	Integer alterar(@BindBean Lista lista);

	@SqlUpdate("DELETE FROM Lista WHERE idLista = :idLista")
	Integer excluir(@Bind("idLista") Long idLista);
}
