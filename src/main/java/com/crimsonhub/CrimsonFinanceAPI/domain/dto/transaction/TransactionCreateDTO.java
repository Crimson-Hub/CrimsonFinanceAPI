package com.crimsonhub.CrimsonFinanceAPI.domain.dto.transaction;

import com.crimsonhub.CrimsonFinanceAPI.custom.AllowedStringValues;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * DTO para criação de transações.
 * <p>
 * Esta classe contém as informações necessárias para criar uma nova transação financeira no sistema.
 * A transação pode estar associada a uma conta ou cartão e contém detalhes sobre o valor, data, descrição,
 * categoria e o tipo da transação.
 * </p>
 *
 * <p><b>Atributos:</b></p>
 * <ul>
 *     <li><b>amount</b>: O valor da transação.</li>
 *     <li><b>createdAt</b>: A data e hora da transação.</li>
 *     <li><b>description</b>: Descrição da transação, explicando o motivo ou detalhes.</li>
 *     <li><b>category</b>: Categoria da transação (por exemplo, "Alimentação", "Transporte", etc.).</li>
 * </ul>
 *
 * @param amount Valor da transação.
 * @param createdAt Data e hora da transação.
 * @param description Descrição da transação.
 * @param category Categoria da transação.
 *
 * @author Crimson Solutions
 * @version 1.0
 * @since 2024-01-01
 */
public record TransactionCreateDTO(@NotNull(message = "{generic.error}") @DecimalMin(value = "0.0") @Digits(integer = 12, fraction = 2) BigDecimal amount,
                                   @NotNull(message = "{generic.error}") Timestamp createdAt,
                                   @NotNull(message = "{generic.error}") @NotEmpty(message = "{generic.error}") String description,
                                   @NotNull(message = "{generic.error}") @NotEmpty(message = "{generic.error}") String category,
                                   @AllowedStringValues(allowedValues = {"EXPENSE", "REVENUE", "TRANSFER"}, message = "{enum.transaction.error}") String type) {
}
