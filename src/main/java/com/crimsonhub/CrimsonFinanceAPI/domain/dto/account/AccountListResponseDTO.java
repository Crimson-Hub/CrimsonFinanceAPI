package com.crimsonhub.CrimsonFinanceAPI.domain.dto.account;

import java.math.BigDecimal;

public record AccountListResponseDTO(Integer id,
                                     BigDecimal initialBalance,
                                     BigDecimal currentBalance,
                                     String companyName,
                                     String accountType) {
}
