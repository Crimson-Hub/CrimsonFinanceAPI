package com.crimsonhub.CrimsonFinanceAPI.domain.dto.transaction;

import java.math.BigDecimal;
import java.sql.Date;

public record TransactionInsertDTO(BigDecimal amount,
                                   Long type,
                                   String description,
                                   Date transactionDate,
                                   Long category) {
}
