package com.crimsonhub.CrimsonFinanceAPI.domain.dto.card;

import java.math.BigDecimal;

public record CardListResponseDTO(Integer id,
                                  BigDecimal creditLimit,
                                  BigDecimal currentExpenses,
                                  String cardFlag,
                                  String description) {
}
