package application.auth;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class TokenService {
    private String tokenKey = "123456789";

    private Date expirationDate() {
        Instant instant = Instant.now().plus(2, ChronoUnit.HOURS);
        return Date.from(instant);
    }

    public String generateToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.tokenKey);

            return JWT.create()
                .withIssuer("Filmes API")
                .withSubject(usuario.getNomeDeUsuario())
                .withExpiresAt(this.expirationDate())
                .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar o token");
        }
    }

    public String getSubject(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.tokenKey);
            return JWT.require(algorithm)
                .withIssuer("Filmes API")
                .build()
                .verify(token)
                .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token Inválido");
        }
    }
    
}