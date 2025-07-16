package desafio.alura.Desafio_Forum.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import desafio.alura.Desafio_Forum.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findByLogin(username);
        if(user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        return user;
    }
}

