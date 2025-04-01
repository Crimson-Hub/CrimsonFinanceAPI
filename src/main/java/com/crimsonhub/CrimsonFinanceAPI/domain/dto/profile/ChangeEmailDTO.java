package com.crimsonhub.CrimsonFinanceAPI.domain.dto.profile;

import jakarta.validation.constraints.*;

/**
 * DTO para alteração de e-mail de um perfil.
 * <p>
 * Esta classe contém as informações necessárias para realizar a alteração de e-mail de um perfil no sistema.
 * É necessário fornecer o e-mail atual, a senha para validação da ação e o novo e-mail a ser atribuído ao perfil.
 * </p>
 *
 * <p><b>Atributos:</b></p>
 * <ul>
 *     <li><b>email</b>: O e-mail atual do perfil, que será verificado antes de permitir a alteração.</li>
 *     <li><b>password</b>: A senha do perfil, usada para validar a solicitação de alteração de e-mail.</li>
 *     <li><b>newEmail</b>: O novo e-mail a ser atribuído ao perfil.</li>
 * </ul>
 *
 * @param email O e-mail atual do perfil a ser alterado.
 * @param password A senha do perfil para validação da alteração.
 * @param newEmail O novo e-mail a ser atribuído ao perfil.
 *
 * @author Crimson Solutions
 * @version 1.0
 * @since 2024-01-01
 */
public record ChangeEmailDTO(@NotNull(message = "{email.error}") @NotEmpty(message = "{email.error}") @NotBlank(message = "{email.error}") @Email(message = "{email.error}") String email,
                             @NotNull(message = "{password.error}") @NotEmpty(message = "{password.error}") @NotBlank(message = "{password.error}") @Size(max = 12, min = 8, message = "{password.size.error}") String password,
                             @NotNull(message = "{email.error}") @NotEmpty(message = "{email.error}") @NotBlank(message = "{email.error}") @Email(message = "{email.error}") String newEmail) {
}
