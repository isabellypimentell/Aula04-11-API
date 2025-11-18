package application.auth;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;  // Serviço para gerar e validar o token

    @Autowired
    private AppUserDataService userDataService;  // Serviço para carregar os detalhes do usuário

    // Método para pegar o token do cabeçalho Authorization
    private String getToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");  // Retorna o token sem o prefixo "Bearer "
        }
        return null;  // Se não houver token, retorna null
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {

        String token = this.getToken(request);  // Tenta obter o token da requisição

        if (token != null && tokenService.isValid(token)) {  // Verifica se o token é válido
            String username = tokenService.getSubject(token);  // Pega o nome de usuário (subject) do token

            // Carrega os detalhes do usuário a partir do banco de dados
            UserDetails user = userDataService.loadUserByUsername(username);

            // Cria o objeto de autenticação com base no usuário carregado
            UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                    user, null, user.getAuthorities());  // `null` pois a senha já está validada pelo token

            // Define a autenticação no contexto de segurança
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } else {
            // Caso o token seja inválido ou ausente, logue a falha (opcional)
            logger.warn("Falha na autenticação: token inválido ou ausente");
        }

        // Continua o fluxo da requisição
        filterChain.doFilter(request, response);
    }
}
