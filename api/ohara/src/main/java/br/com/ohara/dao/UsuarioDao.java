package br.com.ohara.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import br.com.ohara.model.Usuario;

@RegisterBeanMapper(Usuario.class)
public interface UsuarioDao {

	@GetGeneratedKeys
	@SqlUpdate("INSERT INTO Usuario (nome, email, senha, avatar)" + "VALUES (:nome, :email, :senha, :avatar)")
	Long insert(@BindBean Usuario usuario);

	@SqlQuery("SELECT * FROM Usuario WHERE idUsuario = :idUsuario")
	Usuario get(@Bind("idUsuario") Long id);

	@SqlQuery("SELECT * FROM Usuario ORDER BY nome")
	List<Usuario> getAll();

	@SqlUpdate("UPDATE Usuario SET nome = :nome, " + "email = :email, " + "senha = :senha, " + "avatar = :avatar "
			+ "WHERE idUsuario = :idUsuario")
	Integer update(@BindBean Usuario usuario);

	@SqlUpdate("DELETE FROM Usuario WHERE idUsuario = :idUsuario")
	Long delete(@Bind("idUsuario") Long id);

	@SqlQuery("SELECT * FROM usuario WHERE email = :email AND senha = :senha")
	@RegisterBeanMapper(Usuario.class)
	Usuario findByEmailAndSenha(String email, String senha);
}
