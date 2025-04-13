package com.crimsonhub.CrimsonFinanceAPI.domain.dto.card;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CardUpdateDTO(@NotNull(message = "{generic.error}") @DecimalMin(value = "0.0") @Digits(integer = 12, fraction = 2) BigDecimal creditLimit,
                            BigDecimal currentExpenses,
                            String description) {
}
