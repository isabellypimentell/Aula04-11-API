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

    @Value("${token.secret}")
    private String tokenKey;  // A chave do token configurada no application.properties

    // Método para definir a data de expiração do token (2 horas após o momento atual)
    private Instant expirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.UTC);  // Usando UTC para garantir que o fuso horário esteja correto
    }

    // Método para gerar o token JWT
    public String generateToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.tokenKey);  // Usando a chave secreta para o algoritmo HMAC256

            return JWT.create()
                .withIssuer("Filmes API")  // Identificador do emissor
                .withSubject(usuario.getNomeDeUsuario())  // Nome de usuário como o "subject"
                .withExpiresAt(this.expirationDate())  // Define o tempo de expiração
                .sign(algorithm);  // Assina o token com o algoritmo HMAC256
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar o token para o usuário: " + usuario.getNomeDeUsuario(), exception);
        }
    }

    // Método para obter o "subject" (nome de usuário) de um token JWT
    public String getSubject(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.tokenKey);  // Usando a chave secreta para o algoritmo HMAC256
            return JWT.require(algorithm)
                .withIssuer("Filmes API")  // Verifica o emissor do token
                .build()
                .verify(token)  // Verifica a assinatura e a validade
                .getSubject();  // Retorna o "subject" (nome de usuário) do token
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token inválido ou expirado", exception);
        }
    }

    // Método para verificar se o token é válido (não expirado)
    public boolean isValid(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.tokenKey);  // Usando a chave secreta para o algoritmo HMAC256
            JWT.require(algorithm)
                .withIssuer("Filmes API")  // Verifica o emissor do token
                .build()
                .verify(token);  // Se o token for inválido ou expirado, uma exceção será lançada

            return true;  // Token é válido
        } catch (JWTVerificationException exception) {
            return false;  // Caso o token seja inválido ou expirado
        }
    }
}
