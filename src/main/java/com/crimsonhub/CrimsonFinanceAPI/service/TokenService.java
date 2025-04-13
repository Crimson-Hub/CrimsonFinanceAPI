package com.crimsonhub.CrimsonFinanceAPI.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.crimsonhub.CrimsonFinanceAPI.domain.entity.Profile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Serviço responsável pela geração e validação de tokens JWT para autenticação e autorização de usuários.
 * <p>
 * Esta classe fornece funcionalidades para criar tokens JWT para perfis de usuário, bem como validar tokens existentes.
 * </p>
 *
 * @author Crimson Finance
 * @version 1.0
 * @since 2025-01-01
 */
@Service
public class TokenService {

    @Value("${security.jwt.expiration-time}")
    private String expiration;

    @Value("${security.jwt.token-secret}")
    private String secretKey;

    /**
     * Gera um token JWT para o perfil fornecido.
     *
     * @param profile O perfil para o qual o token será gerado.
     * @return Uma string representando o token JWT gerado.
     * @throws RuntimeException Se ocorrer um erro durante a criação do token.
     */
    public String generateToken(Profile profile) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(profile.getEmail())
                    .withClaim("profileId", profile.getId())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);

        } catch (JWTCreationException e) {
            throw new RuntimeException("Error while generating token", e);
        }
    }

    /**
     * Valida um token JWT fornecido e retorna o email do perfil associado ao token.
     *
     * @param token O token JWT a ser validado.
     * @return O email do perfil associado ao token se for válido, caso contrário retorna uma string vazia.
     */
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException e) {
            return "";
        }
    }

    /**
     * Gera a data de expiração do token com base no tempo configurado.
     *
     * @return A data de expiração do token no formato {@link Instant}.
     */
    public Instant genExpirationDate() {
        long expirationTime = Long.parseLong(expiration);
        return LocalDateTime.now().plusHours(expirationTime)
                .atZone(ZoneId.systemDefault()).toInstant();
    }
}
