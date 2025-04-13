package com.crimsonhub.CrimsonFinanceAPI.domain.dto.card;

import java.math.BigDecimal;
import java.sql.Date;

public record InvoiceResponseDTO(Long id,
                                 BigDecimal amountDue,
                                 Date dateDue,
                                 Date closingDate,
                                 boolean paid) {
}
