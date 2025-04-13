package com.crimsonhub.CrimsonFinanceAPI.repository;

import com.crimsonhub.CrimsonFinanceAPI.domain.dto.transaction.TransactionResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.transaction.TransactionTopResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.entity.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Long> {

    @Query(value = "SELECT " +
            "at.id, " +
            "at.amount, " +
            "c.name AS categoryName, " +
            "at.description, " +
            "at.transaction_date AS transactionDate, " +
            "tt.name AS transactionTypeName " +
            "FROM account_transaction at " +
            "JOIN category c ON at.category_id = c.id " +
            "JOIN transaction_type tt ON at.transaction_type_id = tt.id " +
            "WHERE at.account_id = :accountId", nativeQuery = true)
    List<TransactionResponseDTO> findTransactionsByAccountId(Long accountId);

    @Query(value = "SELECT SUM(at.amount) FROM account_transaction at WHERE at.profile_id = :profileId AND at.transaction_type_id = :transactionTypeId", nativeQuery = true)
    BigDecimal getTotalAmountByTransactionType(Long profileId, Long transactionTypeId);

    @Query(value = "SELECT at.id, at.amount, c.name AS categoryName FROM account_transaction at " +
            "JOIN category c ON at.category_id = c.id " +
            "WHERE at.profile_id = :profileId " +
            "AND at.transaction_type_id = :type " +
            "ORDER BY at.amount DESC limit 5", nativeQuery = true)
    List<TransactionTopResponseDTO> findTopTransactionsByType(Long profileId, Long type);
}
