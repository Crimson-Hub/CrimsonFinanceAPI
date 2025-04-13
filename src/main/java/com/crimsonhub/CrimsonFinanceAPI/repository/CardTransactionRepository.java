package com.crimsonhub.CrimsonFinanceAPI.repository;

import com.crimsonhub.CrimsonFinanceAPI.domain.dto.transaction.TransactionResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.entity.CardTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CardTransactionRepository extends JpaRepository<CardTransaction, Long> {

    @Query(value = "SELECT " +
            "ct.id, " +
            "ct.amount, " +
            "c.name AS categoryName, " +
            "ct.description, " +
            "ct.transaction_date AS transactionDate, " +
            "tt.name AS transactionTypeName " +
            "FROM card_transaction ct " +
            "JOIN category c ON ct.category_id = c.id " +
            "JOIN transaction_type tt ON ct.transaction_type_id = tt.id " +
            "WHERE ct.card_id = :cardId", nativeQuery = true)
    List<TransactionResponseDTO> findTransactionsByCardId(Long cardId);

    @Query(value = "SELECT SUM(ct.amount) FROM card_transaction ct WHERE ct.profile_id = :profileId AND ct.transaction_type_id = 2", nativeQuery = true)
    BigDecimal getTotalExpensesForCard(Long profileId);
}
