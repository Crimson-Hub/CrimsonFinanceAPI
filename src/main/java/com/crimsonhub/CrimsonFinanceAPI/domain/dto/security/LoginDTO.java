package com.crimsonhub.CrimsonFinanceAPI.domain.dto.security;

import jakarta.validation.constraints.*;

/**
 * DTO para login de usuário.
 * <p>
 * Esta classe contém as informações necessárias para a autenticação de um usuário no sistema, incluindo
 * o e-mail e a senha. É utilizada em operações de login, onde essas credenciais são verificadas.
 * </p>
 *
 * <p><b>Atributos:</b></p>
 * <ul>
 *     <li><b>email</b>: E-mail do usuário para autenticação.</li>
 *     <li><b>password</b>: Senha do usuário para autenticação.</li>
 * </ul>
 *
 * @param email E-mail do usuário para autenticação.
 * @param password Senha do usuário para autenticação.
 *
 * @author Crimson Solutions
 * @version 1.0
 * @since 2024-01-01
 */
public record LoginDTO(@NotNull(message = "{email.error}") @NotEmpty(message = "{email.error}") @NotBlank(message = "{email.error}") @Email(message = "{email.error}") String email,
                       @NotNull(message = "{password.error}") @NotEmpty(message = "{password.error}") @NotBlank(message = "{password.error}") @Size(max = 12, min = 8, message = "{password.size.error}") String password) {
}
