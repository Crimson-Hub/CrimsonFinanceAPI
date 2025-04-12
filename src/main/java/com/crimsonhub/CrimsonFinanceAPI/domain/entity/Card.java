package com.crimsonhub.CrimsonFinanceAPI.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "card")
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    @Column(name = "credit_limit", precision = 12, scale = 2, nullable = false)
    private BigDecimal creditLimit;

    @Column(name = "current_expenses", precision = 12, scale = 2, nullable = false)
    private BigDecimal currentExpenses = BigDecimal.ZERO;

    @Column(name = "card_flag_id", nullable = false)
    private Long flag;

    @Column(name = "description", nullable = false, length = 40)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", unique = true)
    private Set<CardTransaction> transactions;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", unique = true)
    private Set<Invoice> invoices;
}
