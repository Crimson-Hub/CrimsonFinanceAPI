package com.crimsonhub.CrimsonFinanceAPI.repository;

import com.crimsonhub.CrimsonFinanceAPI.domain.dto.transaction.TransactionResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.entity.odl.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Repositório para a entidade {@link Transaction}.
 * <p>
 * Esta interface gerencia as operações de persistência para a entidade {@link Transaction}.
 * Ela herda de {@link JpaRepository}, fornecendo métodos padrão para CRUD de transações.
 * </p>
 *
 * <p><b>Annotations:</b></p>
 * <ul>
 *     <li>{@link Repository}: Define a classe como um repositório.</li>
 * </ul>
 *
 * @author Crimson Solutions
 * @version 1.0
 * @since 2024-01-01
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT SUM(amount) FROM transaction WHERE id_account = :accountId AND type = 1", nativeQuery = true)
    String getRevenuesBalance(Long accountId);

    @Query(value = "SELECT SUM(amount) FROM transaction WHERE id_account = :accountId AND type = 0", nativeQuery = true)
    String getExpensesBalance(Long accountId);

    @Query(value = "SELECT SUM(amount) FROM transaction WHERE id_card = :cardId AND type = 3", nativeQuery = true)
    String getCardExpensesBalance(Long cardId);

    @Query(value = "SELECT id, amount, category FROM transaction WHERE id_account = :accountId AND type = 1 ORDER BY amount DESC limit 5", nativeQuery = true)
    Set<TransactionResponseDTO> getRevenueTransactionsTop(Long accountId);

    @Query(value = "SELECT id, amount, category FROM transaction WHERE id_account = :accountId AND type = 0 ORDER BY amount DESC limit 5", nativeQuery = true)
    Set<TransactionResponseDTO> getExpenseTransactionsTop(Long accountId);
}
