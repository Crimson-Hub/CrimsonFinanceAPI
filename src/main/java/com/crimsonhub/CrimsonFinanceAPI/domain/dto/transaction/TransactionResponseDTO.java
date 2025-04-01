package com.crimsonhub.CrimsonFinanceAPI.domain.dto.transaction;

import java.math.BigDecimal;

public record TransactionResponseDTO(Integer id, BigDecimal amount, String category) {
}
