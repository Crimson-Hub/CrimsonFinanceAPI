package com.crimsonhub.CrimsonFinanceAPI.domain.dto.profile;

import java.util.Date;

/**
 * Classe de registro que representa os detalhes completos de um perfil.
 * <p>
 * Essa classe encapsula informações pessoais, como nome completo, nome preferido,
 * data de nascimento, telefone, nacionalidade, número de identificação e CEP.
 * </p>
 *
 * @param email Email associado ao perfil.
 * @param fullName Nome completo do perfil.
 * @param preferredName Nome preferido pelo usuário.
 * @param birthday Data de nascimento do usuário.
 * @param phone Número de telefone do usuário.
 * @param nationality Nacionalidade do usuário.
 * @param identificationNumber Número de identificação (como CPF ou equivalente).
 * @param cep Código postal (CEP) associado ao perfil.
 *
 * @author Crimson Finance
 * @version 1.0
 * @since 2025-01-01
 */
public record ProfileResponseDTO(String email,
                                String fullName,
                                String preferredName,
                                Date birthday,
                                String phone,
                                String nationality,
                                String identificationNumber,
                                String cep) {
}
