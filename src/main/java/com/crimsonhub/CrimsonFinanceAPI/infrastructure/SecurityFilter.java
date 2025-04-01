package com.crimsonhub.CrimsonFinanceAPI.infrastructure;

import com.crimsonhub.CrimsonFinanceAPI.service.ProfileService;
import com.crimsonhub.CrimsonFinanceAPI.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro de segurança responsável pela validação de tokens JWT e configuração do contexto de autenticação.
 * <p>
 * Essa classe intercepta todas as requisições HTTP e verifica a presença de um token JWT no cabeçalho "Authorization".
 * Se o token for válido, a autenticação do usuário é configurada no contexto de segurança do Spring Security.
 * </p>
 *
 * @see OncePerRequestFilter
 * @author Crimson Finance
 * @version 1.0
 * @since 2025-01-01
 */
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ProfileService profileService;

    /**
     * Método principal do filtro que executa a lógica de validação do token.
     *
     * @param request     Objeto {@link HttpServletRequest} contendo os detalhes da requisição.
     * @param response    Objeto {@link HttpServletResponse} usado para configurar a resposta.
     * @param filterChain Objeto {@link FilterChain} para delegar a execução para outros filtros.
     * @throws ServletException Lançada se ocorrer um erro durante o processamento do filtro.
     * @throws IOException      Lançada se ocorrer um erro de I/O durante o processamento do filtro.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);

        if (token != null) {
            var email = tokenService.validateToken(token);
            UserDetails profile = profileService.loadUserByUsername(email);

            var authentication = new UsernamePasswordAuthenticationToken(profile, null, profile.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    /**
     * Método auxiliar para recuperar o token JWT do cabeçalho "Authorization".
     *
     * @param request Objeto {@link HttpServletRequest} contendo os detalhes da requisição.
     * @return Uma string contendo o token JWT, ou {@code null} se o cabeçalho não estiver presente.
     */
    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
