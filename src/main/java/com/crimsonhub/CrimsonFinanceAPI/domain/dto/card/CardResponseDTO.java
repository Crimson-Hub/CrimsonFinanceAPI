package com.crimsonhub.CrimsonFinanceAPI.domain.dto.card;

import java.math.BigDecimal;

/**
 * DTO para resposta de dados de um cartão.
 * <p>
 * Esta classe contém as informações essenciais de um cartão financeiro que são retornadas em operações
 * de consulta ou listagem no sistema.
 * </p>
 *
 * <p><b>Atributos:</b></p>
 * <ul>
 *     <li><b>expiryDay</b>: Dia de validade do cartão, representando o mês e o dia de expiração.</li>
 *     <li><b>creditLimit</b>: Limite de crédito associado ao cartão.</li>
 * </ul>
 *
 * @param expiryDay Dia de validade do cartão (mês e dia de expiração).
 * @param creditLimit Limite de crédito associado ao cartão.
 *
 * @author Crimson Solutions
 * @version 1.0
 * @since 2024-01-01
 */
public record CardResponseDTO(Long id,
                              int expiryDay,
                              BigDecimal creditLimit) {
}
