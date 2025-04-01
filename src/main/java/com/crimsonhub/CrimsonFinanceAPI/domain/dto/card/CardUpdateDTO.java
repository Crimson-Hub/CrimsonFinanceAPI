package com.crimsonhub.CrimsonFinanceAPI.domain.dto.card;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * DTO para atualização de dados de um cartão.
 * <p>
 * Esta classe contém as informações necessárias para atualizar os dados de um cartão existente no sistema,
 * incluindo a data de validade e o limite de crédito.
 * </p>
 *
 * <p><b>Atributos:</b></p>
 * <ul>
 *     <li><b>expiryDay</b>: Novo dia de validade do cartão, representando o mês e o dia de expiração.</li>
 *     <li><b>creditLimit</b>: Novo limite de crédito a ser atribuído ao cartão.</li>
 * </ul>
 *
 * @param expiryDay Novo dia de validade do cartão (mês e dia de expiração).
 * @param creditLimit Novo limite de crédito a ser atribuído ao cartão.
 *
 * @author Crimson Solutions
 * @version 1.0
 * @since 2024-01-01
 */
public record CardUpdateDTO(@NotNull(message = "{generic.error}") Integer expiryDay,
                            @NotNull(message = "{generic.error}") @DecimalMin(value = "0.0") @Digits(integer = 12, fraction = 2) BigDecimal creditLimit) {
}
