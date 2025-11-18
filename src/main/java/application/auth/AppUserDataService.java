package application.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserDataService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepo.findByNomeDeUsuario(username);

        if (usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        // Agora usamos o método getRolesAsList() para obter as roles do usuário como lista
        String[] roles = usuario.get().getRolesAsList().toArray(new String[0]);

        return User.builder()
            .username(usuario.get().getNomeDeUsuario())
            .password(usuario.get().getSenha()) // A senha já deve estar codificada no banco
            .roles(roles) // Aqui são atribuídas as roles do usuário
            .build();
    }
    
    // Método de codificação de senha para uso na criação de usuário
    public String encodePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
