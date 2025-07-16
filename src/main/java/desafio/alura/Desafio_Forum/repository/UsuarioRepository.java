package desafio.alura.Desafio_Forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import desafio.alura.Desafio_Forum.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findByLogin(String login);
}
