package com.crimsonhub.CrimsonFinanceAPI.domain.dto.profile;

import jakarta.validation.constraints.*;

/**
 * DTO para alteração de senha de um perfil.
 * <p>
 * Esta classe contém as informações necessárias para realizar a alteração de senha de um perfil no sistema.
 * Para a alteração, são fornecidos o e-mail do perfil, a senha atual para validação da solicitação e a nova senha a ser definida.
 * </p>
 *
 * <p><b>Atributos:</b></p>
 * <ul>
 *     <li><b>email</b>: O e-mail do perfil para o qual a senha será alterada.</li>
 *     <li><b>password</b>: A senha atual do perfil, utilizada para validar a solicitação de alteração.</li>
 *     <li><b>newPassword</b>: A nova senha que será atribuída ao perfil.</li>
 * </ul>
 *
 * @param email O e-mail do perfil para o qual a senha será alterada.
 * @param password A senha atual do perfil para validação da alteração.
 * @param newPassword A nova senha a ser atribuída ao perfil.
 *
 * @author Crimson Solutions
 * @version 1.0
 * @since 2024-01-01
 */
public record ChangePasswordDTO(@NotNull(message = "{email.error}") @NotEmpty(message = "{email.error}") @NotBlank(message = "{email.error}") @Email(message = "{email.error}") String email,
                                @NotNull(message = "{password.error}") @NotEmpty(message = "{password.error}") @NotBlank(message = "{password.error}") @Size(max = 12, min = 8, message = "{password.size.error}") String password,
                                @NotNull(message = "{password.error}") @NotEmpty(message = "{password.error}") @NotBlank(message = "{password.error}") @Size(max = 12, min = 8, message = "{password.size.error}") String newPassword) {
}
