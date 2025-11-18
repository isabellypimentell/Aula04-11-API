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

        // Obtendo as roles do usuário e convertendo para um array de Strings
        // Supondo que o método getRoles() retorne uma lista de objetos Role
        String[] roles = usuario.get().getRoles().stream()
                                 .map(role -> role.getNome()) // Ou use outro método se for outra estrutura
                                 .toArray(String[]::new);

        return User.builder()
            .username(usuario.get().getNomeDeUsuario())
            .password(usuario.get().getSenha()) // A senha deve já estar codificada no banco
            .roles(roles) // Atribuindo as roles ao usuário
            .build();
    }

    // Método de codificação de senha para uso na criação de usuários
    public String encodePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password); // Retorna a senha codificada
    }

    // Método para salvar ou atualizar usuário, garantindo que a senha esteja codificada
    public Usuario saveOrUpdateUsuario(Usuario usuario) {
        // Codificando a senha antes de salvar no banco de dados
        if (usuario.getSenha() != null && !usuario.getSenha().isEmpty()) {
            usuario.setSenha(encodePassword(usuario.getSenha()));
        }
        return usuarioRepo.save(usuario); // Salvando ou atualizando o usuário
    }
}
