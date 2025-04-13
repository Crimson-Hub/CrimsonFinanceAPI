package com.crimsonhub.CrimsonFinanceAPI.domain.dto.profile;

import java.util.Date;

/**
 * DTO para resposta resumida de dados de um perfil.
 * <p>
 * Esta classe contém uma versão resumida das informações de um perfil, retornando apenas o identificador,
 * e-mail e data de criação do perfil, adequada para listagens ou respostas com informações básicas.
 * </p>
 *
 * <p><b>Atributos:</b></p>
 * <ul>
 *     <li><b>id</b>: Identificador único do perfil.</li>
 *     <li><b>email</b>: E-mail associado ao perfil.</li>
 *     <li><b>createdAt</b>: Data de criação do perfil.</li>
 * </ul>
 *
 * @param id Identificador único do perfil.
 * @param email E-mail associado ao perfil.
 *
 * @author Crimson Solutions
 * @version 1.0
 * @since 2024-01-01
 */
public record ProfileSummaryResponseDTO(Long id,
                                        String email) {
}
