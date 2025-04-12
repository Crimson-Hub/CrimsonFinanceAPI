package com.crimsonhub.CrimsonFinanceAPI.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "invoice")
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount_due", precision = 12, scale = 2, nullable = false)
    private BigDecimal amountDue;

    @Column(name = "date_due", nullable = false)
    private Date dateDue;

    @Column(name = "closing_date", nullable = false)
    private Date closingDate;

    @Column(name = "paid", nullable = false)
    private boolean paid = false;
}
