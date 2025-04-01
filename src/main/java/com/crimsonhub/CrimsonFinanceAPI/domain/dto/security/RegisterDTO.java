package com.crimsonhub.CrimsonFinanceAPI.domain.dto.security;

import jakarta.validation.constraints.*;

import java.sql.Timestamp;

/**
 * DTO para registro de usuário.
 * <p>
 * Esta classe contém as informações necessárias para registrar um novo usuário no sistema.
 * Ela inclui o e-mail, senha, nome completo, número de identificação, nacionalidade e a data de criação
 * do registro. É utilizada durante o processo de criação de uma nova conta de usuário.
 * </p>
 *
 * <p><b>Atributos:</b></p>
 * <ul>
 *     <li><b>email</b>: E-mail do usuário, utilizado como identificador único para login.</li>
 *     <li><b>password</b>: Senha do usuário para autenticação.</li>
 *     <li><b>fullName</b>: Nome completo do usuário.</li>
 *     <li><b>identificationNumber</b>: Número de identificação do usuário (ex: CPF, RG, etc.), deve ser único.</li>
 *     <li><b>nationality</b>: Nacionalidade do usuário.</li>
 *     <li><b>createdAt</b>: Data e hora de criação do registro de usuário.</li>
 * </ul>
 *
 * @param email E-mail do usuário, utilizado para login.
 * @param password Senha do usuário para autenticação.
 * @param fullName Nome completo do usuário.
 * @param identificationNumber Número de identificação do usuário.
 * @param nationality Nacionalidade do usuário.
 * @param createdAt Data e hora de criação do registro de usuário.
 *
 * @author Crimson Solutions
 * @version 1.0
 * @since 2024-01-01
 */
public record RegisterDTO(@NotNull(message = "{email.error}") @NotEmpty(message = "{email.error}") @NotBlank(message = "{email.error}") @Email(message = "{email.error}") String email,
                          @NotNull(message = "{password.error}") @NotEmpty(message = "{password.error}") @NotBlank(message = "{password.error}") @Size(max = 12, min = 8, message = "{password.size.error}") String password,
                          @NotNull(message = "{generic.error}") @NotEmpty(message = "{generic.error}") @NotBlank(message = "{generic.error}") @Size(max = 60, message = "{name.size.error}") String fullName,
                          @NotNull(message = "{generic.error}") @NotEmpty(message = "{generic.error}") @NotEmpty(message = "{generic.error}") @Size(min = 11, message = "{identificationnumber.size.error}") String identificationNumber,
                          @NotNull(message = "{generic.error}") @NotEmpty(message = "{generic.error}") @NotEmpty(message = "{generic.error}") String nationality,
                          @NotNull(message = "{generic.error}") Timestamp createdAt) {
}
