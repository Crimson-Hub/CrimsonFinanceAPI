package com.crimsonhub.CrimsonFinanceAPI.domain.dto.account;

import com.crimsonhub.CrimsonFinanceAPI.custom.AllowedStringValues;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * DTO para criação de uma nova conta financeira.
 * <p>
 * Esta classe define os dados necessários para a criação de uma conta no sistema.
 * Utiliza o recurso <code>record</code> do Java para imutabilidade e simplificação do código.
 * </p>
 *
 * <p><b>Atributos:</b></p>
 * <ul>
 *     <li><b>initialBalance</b>: Saldo inicial da conta.</li>
 *     <li><b>company</b>: Nome da instituição ou empresa associada à conta.</li>
 *     <li><b>homeScreen</b>: Indica se a conta será exibida na tela inicial.</li>
 *     <li><b>createdAt</b>: Data de criação da conta.</li>
 * </ul>
 *
 * @param initialBalance Saldo inicial da conta.
 * @param company Nome da instituição ou empresa associada à conta.
 * @param homeScreen Indica se a conta será exibida na tela inicial.
 * @param createdAt Data de criação da conta.
 *
 * @author Crimson Solutions
 * @version 1.0
 * @since 2024-01-01
 */
public record AccountCreateDTO(@NotNull(message = "{generic.error}") @DecimalMin(value = "0.0") @Digits(integer = 12, fraction = 2) BigDecimal initialBalance,
                               @NotNull(message = "{generic.error}") @NotEmpty(message = "{generic.error}") @NotBlank(message = "{generic.error}") String company,
                               @AllowedStringValues(allowedValues = {"CURRENT_ACCOUNT", "WALLET_ACCOUNT", "SAVINGS_ACCOUNT", "INVESTMENT_ACCOUNT", "OTHERS"}, message = "{enum.account.error}") String type,
                               @NotNull(message = "{generic.error}") Boolean homeScreen,
                               @NotNull(message = "{generic.error}") Timestamp createdAt) {
}