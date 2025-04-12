package com.crimsonhub.CrimsonFinanceAPI.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "account")
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    @Column(name = "initial_balance", precision = 12, scale = 2, nullable = false)
    private BigDecimal initialBalance;

    @Column(name = "current_balance", precision = 12, scale = 2, nullable = false)
    private BigDecimal currentBalance;

    @Column(name = "account_company_id", nullable = false)
    private Long company;

    @Column(name = "account_type_id", nullable = false)
    private Long type;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", unique = true)
    private Set<AccountTransaction> transactions;
}

