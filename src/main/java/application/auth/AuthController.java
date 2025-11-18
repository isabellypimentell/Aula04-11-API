package application.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    // DTO de resposta com o token
    public static class AuthResponse {
        private String token;

        public AuthResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    @PostMapping
    public AuthResponse login(@RequestBody Usuario usuario) {
        try {
            // Autentica o usuário com os dados fornecidos
            UsernamePasswordAuthenticationToken tk =
                new UsernamePasswordAuthenticationToken(usuario.getNomeDeUsuario(), usuario.getSenha());
            authenticationManager.authenticate(tk);

            // Gera o token de autenticação
            String token = tokenService.generateToken(usuario);

            // Retorna o token como resposta
            return new AuthResponse(token);
        } catch (AuthenticationException e) {
            // Caso a autenticação falhe, retorna uma mensagem de erro apropriada
            throw new RuntimeException("Usuário ou senha inválidos", e);
        }
    }
}
