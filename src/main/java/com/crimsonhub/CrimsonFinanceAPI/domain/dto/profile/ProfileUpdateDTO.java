package com.crimsonhub.CrimsonFinanceAPI.domain.dto.profile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

/**
 * DTO para atualização de perfil.
 * <p>
 * Esta classe contém as informações necessárias para realizar a atualização de um perfil no sistema.
 * Inclui o e-mail, a senha para validação da ação, e um objeto que representa os detalhes atualizados do perfil.
 * </p>
 *
 * <p><b>Atributos:</b></p>
 * <ul>
 *     <li><b>email</b>: O e-mail atualizado do perfil. Pode ser utilizado para validação ou atualização do e-mail.</li>
 *     <li><b>password</b>: A senha atual do perfil, usada para validar a solicitação de atualização.</li>
 *     <li><b>updatedProfile</b>: Objeto contendo os detalhes atualizados do perfil (ver classe interna {@link UpdatedProfile}).</li>
 * </ul>
 *
 * @param email E-mail atualizado do perfil.
 * @param password Senha atual do perfil para validação da atualização.
 * @param updatedProfile Objeto contendo os detalhes atualizados do perfil.
 *
 * @author Crimson Solutions
 * @version 1.0
 * @since 2024-01-01
 */
public record ProfileUpdateDTO(String email,
                               String password,
                               UpdatedProfile updatedProfile) {

    /**
     * DTO para atualização dos detalhes pessoais de um perfil.
     * <p>
     * Esta classe contém as informações que podem ser atualizadas no perfil, como o nome completo, nome preferido,
     * data de nascimento, telefone, nacionalidade e código postal (CEP).
     * </p>
     *
     * <p><b>Atributos:</b></p>
     * <ul>
     *     <li><b>fullName</b>: Nome completo do titular do perfil.</li>
     *     <li><b>preferredName</b>: Nome preferido do titular do perfil.</li>
     *     <li><b>birthday</b>: Data de nascimento do titular do perfil.</li>
     *     <li><b>phone</b>: Número de telefone atualizado do titular do perfil.</li>
     *     <li><b>nationality</b>: Nacionalidade do titular do perfil.</li>
     *     <li><b>cep</b>: Código postal (CEP) do endereço do titular.</li>
     * </ul>
     *
     * @param fullName Nome completo do titular do perfil.
     * @param preferredName Nome preferido do titular do perfil.
     * @param birthday Data de nascimento do titular do perfil.
     * @param phone Número de telefone atualizado do titular do perfil.
     * @param nationality Nacionalidade do titular do perfil.
     * @param cep Código postal (CEP) do endereço do titular.
     *
     * @author Crimson Solutions
     * @version 1.0
     * @since 2024-01-01
     */
    public record UpdatedProfile(@NotNull(message = "{generic.error}") @NotEmpty(message = "{generic.error}") @NotBlank(message = "{generic.error}") @Size(max = 60, message = "{name.size.error}") String fullName,
                                 @NotNull(message = "{generic.error}") @NotEmpty(message = "{generic.error}") @NotBlank(message = "{generic.error}") @Size(max = 60, message = "{name.size.error}") String preferredName,
                                 @NotNull(message = "{generic.error}") Date birthday,
                                 @NotNull(message = "{phone.error}") @NotEmpty(message = "{phone.error}") @NotBlank(message = "{phone.error}") @Size(max = 13, message = "{phone.size.error}") String phone,
                                 @NotNull(message = "{generic.error}") @NotEmpty(message = "{generic.error}") @NotBlank(message = "{generic.error}") String nationality,
                                 @NotNull(message = "{cep.error}") @NotEmpty(message = "{cep.error}") @NotBlank(message = "{cep.error}") @Size(max = 13, message = "{cep.size.error}") String cep) {
    }
}
