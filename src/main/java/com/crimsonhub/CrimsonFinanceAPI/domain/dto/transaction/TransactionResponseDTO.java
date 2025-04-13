package com.crimsonhub.CrimsonFinanceAPI.domain.dto.transaction;

import java.math.BigDecimal;
import java.sql.Date;

public record TransactionResponseDTO(Integer id,
                                     BigDecimal amount,
                                     String transactionTypeName,
                                     String description,
                                     Date transactionDate,
                                     String categoryName) {
}
