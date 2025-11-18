package application.auth;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class TokenService {

    // A chave do token agora pode ser configurada via arquivo de propriedades
    @Value("${token.secret}")
    private String tokenKey;  // A chave do token pode ser configurada no application.properties ou em variáveis de ambiente

    // Método para definir a data de expiração do token (2 horas após o momento atual)
    private Instant expirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.UTC);  // Usando UTC em vez de um fuso horário fixo
    }

    // Método para gerar o token JWT
    public String generateToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.tokenKey);  // Usando a chave secreta para o algoritmo HMAC256

            // Criação do token JWT
            return JWT.create()
                .withIssuer("Filmes API")  // Identificador do emissor
                .withSubject(usuario.getNomeDeUsuario())  // Nome de usuário como o "subject"
                .withExpiresAt(this.expirationDate())  // Define o tempo de expiração
                .sign(algorithm);  // Assina o token com o algoritmo HMAC256
        } catch (JWTCreationException exception) {
            // Melhora no tratamento de exceções: exibe uma mensagem mais informativa
            throw new RuntimeException("Erro ao gerar o token para o usuário: " + usuario.getNomeDeUsuario(), exception);
        }
    }

    // Método para obter o "subject" (nome de usuário) de um token JWT
    public String getSubject(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.tokenKey);  // Usando a chave secreta para o algoritmo HMAC256
            // Valida e decodifica o token
            return JWT.require(algorithm)
                .withIssuer("Filmes API")  // Verifica o emissor do token
                .build()
                .verify(token)  // Verifica a assinatura e a validade
                .getSubject();  // Retorna o "subject" (nome de usuário) do token
        } catch (JWTVerificationException exception) {
            // Exceção mais detalhada para tokens inválidos
            throw new RuntimeException("Token inválido ou expirado", exception);
        }
    }
}
