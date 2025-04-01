package com.crimsonhub.CrimsonFinanceAPI.domain.dto.account;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

/**
 * DTO para atualização de dados de uma conta financeira.
 * <p>
 * Esta classe representa os dados necessários para realizar a atualização de uma conta existente no sistema.
 * Ela contém informações que podem ser alteradas após a criação da conta.
 * </p>
 *
 * <p><b>Atributos:</b></p>
 * <ul>
 *     <li><b>currentBalance</b>: Saldo atual da conta, que pode ser atualizado.</li>
 *     <li><b>company</b>: Nome da instituição ou empresa associada à conta, que pode ser alterado.</li>
 *     <li><b>homeScreen</b>: Indica se a conta deve ser exibida na tela inicial, podendo ser alterado.</li>
 * </ul>
 *
 * @param currentBalance Saldo atual da conta, que pode ser atualizado.
 * @param company Nome da instituição ou empresa associada à conta, podendo ser alterado.
 * @param homeScreen Indica se a conta será exibida na tela inicial.
 *
 * @author Crimson Solutions
 * @version 1.0
 * @since 2024-01-01
 */
public record AccountUpdateDTO(@NotNull(message = "{generic.error}") @DecimalMin(value = "0.0") @Digits(integer = 12, fraction = 2) BigDecimal currentBalance,
                               @NotNull(message = "{generic.error}") @NotEmpty(message = "{generic.error}") @NotBlank(message = "{generic.error}") String company,
                               @NotNull(message = "{generic.error}") Boolean homeScreen) {
}
