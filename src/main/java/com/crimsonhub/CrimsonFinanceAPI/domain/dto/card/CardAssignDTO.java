package com.crimsonhub.CrimsonFinanceAPI.domain.dto.card;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * DTO para atribuição de um cartão a um perfil.
 * <p>
 * Esta classe contém os dados necessários para a atribuição de um cartão a um perfil existente no sistema.
 * As informações fornecidas são usadas para configurar um novo cartão com limite de crédito e data de validade.
 * </p>
 *
 * <p><b>Atributos:</b></p>
 * <ul>
 *     <li><b>expiryDay</b>: Dia de validade do cartão, representando o mês e dia de expiração.</li>
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
public record CardAssignDTO(@NotNull(message = "{generic.error}") Integer expiryDay,
                            @NotNull(message = "{generic.error}") @DecimalMin(value = "0.0") @Digits(integer = 12, fraction = 2) BigDecimal creditLimit) {
}

