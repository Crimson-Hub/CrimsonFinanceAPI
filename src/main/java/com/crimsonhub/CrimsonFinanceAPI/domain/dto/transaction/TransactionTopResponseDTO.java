package com.crimsonhub.CrimsonFinanceAPI.domain.dto.transaction;

import java.math.BigDecimal;

public record TransactionTopResponseDTO(Integer id,
                                        BigDecimal amount,
                                        String categoryName) {
}
