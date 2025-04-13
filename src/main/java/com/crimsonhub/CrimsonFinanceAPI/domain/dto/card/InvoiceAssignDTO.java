package com.crimsonhub.CrimsonFinanceAPI.domain.dto.card;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.sql.Date;

public record InvoiceAssignDTO(@NotNull(message = "{generic.error}") @DecimalMin(value = "0.0") @Digits(integer = 12, fraction = 2) BigDecimal amountDue,
                               @NotNull(message = "{generic.error}") Date dateDue,
                               @NotNull(message = "{generic.error}") Date closingDate,
                               @NotNull(message = "{generic.error}") Boolean paid) {
}
