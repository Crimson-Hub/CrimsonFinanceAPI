package com.crimsonhub.CrimsonFinanceAPI.repository;

import com.crimsonhub.CrimsonFinanceAPI.domain.dto.account.AccountListResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value = "SELECT " +
            "a.id, " +
            "a.initial_balance AS initialBalance, " +
            "a.current_balance AS currentBalance, " +
            "ac.name AS companyName, " +
            "act.name AS accountType " +
            "FROM account a " +
            "JOIN account_company ac ON a.account_company_id = ac.id " +
            "JOIN account_type act ON a.account_type_id = act.id " +
            "WHERE a.profile_id = :profileId",
            nativeQuery = true)
    List<AccountListResponseDTO> findAccountsByProfileId(Long profileId);

    @Query("SELECT SUM(a.currentBalance) FROM Account a WHERE a.profile.id = :profileId")
    BigDecimal getTotalAccountsBalance(Long profileId);
}
