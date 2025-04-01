package com.crimsonhub.CrimsonFinanceAPI.domain.dto.security;

/**
 * DTO para resposta de token de autenticação.
 * <p>
 * Esta classe é usada para representar a resposta contendo o token gerado durante o processo de autenticação de um usuário.
 * O token é utilizado para validar as requisições subsequentes no sistema, geralmente em APIs que exigem autenticação.
 * </p>
 *
 * <p><b>Atributos:</b></p>
 * <ul>
 *     <li><b>token</b>: O token gerado, geralmente um JWT (JSON Web Token), que pode ser usado para autenticação em requisições futuras.</li>
 * </ul>
 *
 * @param token O token de autenticação gerado após o login ou registro.
 *
 * @author Crimson Solutions
 * @version 1.0
 * @since 2024-01-01
 */
public record TokenResponseDTO(String token) { }
