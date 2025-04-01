package com.crimsonhub.CrimsonFinanceAPI.domain.dto.card;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

/**
 * DTO para atribuição de uma fatura a um cartão.
 * <p>
 * Esta classe contém os dados necessários para atribuir uma fatura a um cartão específico,
 * incluindo o valor devido, a data de vencimento e o status de pagamento da fatura.
 * </p>
 *
 * <p><b>Atributos:</b></p>
 * <ul>
 *     <li><b>amountDue</b>: Valor total devido na fatura.</li>
 *     <li><b>dateDue</b>: Data de vencimento da fatura.</li>
 *     <li><b>paid</b>: Indica se a fatura foi paga ou não.</li>
 * </ul>
 *
 * @param amountDue Valor total devido na fatura.
 * @param dateDue Data de vencimento da fatura.
 * @param paid Indica se a fatura foi paga (true) ou não (false).
 *
 * @author Crimson Solutions
 * @version 1.0
 * @since 2024-01-01
 */
public record InvoiceAssignDTO(@NotNull(message = "{generic.error}") @DecimalMin(value = "0.0") @Digits(integer = 12, fraction = 2) BigDecimal amountDue,
                               @NotNull(message = "{generic.error}") Date dateDue,
                               @NotNull(message = "{generic.error}") Boolean paid) {
}
