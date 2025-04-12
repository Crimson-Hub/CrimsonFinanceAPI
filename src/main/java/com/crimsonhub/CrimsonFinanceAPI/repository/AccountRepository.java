package com.crimsonhub.CrimsonFinanceAPI.repository;

import com.crimsonhub.CrimsonFinanceAPI.domain.dto.account.AccountResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.entity.Account;
import com.crimsonhub.CrimsonFinanceAPI.domain.entity.odl.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Repositório para a entidade {@link Account}.
 * <p>
 * Esta interface gerencia as operações de persistência para a entidade {@link Account}.
 * Ela herda de {@link JpaRepository}, fornecendo métodos padrão para CRUD, além de consultas personalizadas
 * com o uso da anotação {@link Query}.
 * </p>
 *
 * <p><b>Consultas Personalizadas:</b></p>
 * <ul>
 *     <li><b>findAccountsByProfileId</b>: Retorna uma lista de {@link AccountResponseDTO} com informações detalhadas de contas associadas a um perfil, baseado no ID do perfil.</li>
 *     <li><b>findTransactionsByAccountId</b>: Retorna um conjunto de transações associadas a uma conta específica, baseado no ID da conta.</li>
 * </ul>
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
public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * Retorna uma lista de {@link AccountResponseDTO} contendo informações detalhadas das contas associadas ao ID de um perfil.
     *
     * @param profileId ID do perfil ao qual as contas estão associadas.
     * @return Lista de {@link AccountResponseDTO} com informações das contas associadas ao perfil.
     */
    @Query("SELECT new com.crimsonhub.CrimsonFinanceAPI.domain.dto.account.AccountResponseDTO(" +
            "a.id, a.initialBalance, a.currentBalance, a.company, a.accountType, " +
            "a.amountExpenses, a.amountRevenues, a.amountTransfers, " +
            "a.homeScreen, a.createdAt" +
            ") FROM Account a WHERE a.profile.id = :profileId")
    Set<AccountResponseDTO> findAccountsByProfileId(Long profileId);

    /**
     * Retorna um conjunto de {@link Transaction} associadas a uma conta, identificado pelo seu ID.
     *
     * @param id ID da conta da qual as transações serão recuperadas.
     * @return Conjunto de {@link Transaction} associadas à conta.
     */
    @Query("SELECT a.transactions FROM Account a WHERE a.id = :id")
    Set<Transaction> findTransactionsByAccountId(Long id);

    @Query(value = "SELECT SUM(current_balance) FROM account WHERE id_profile = :profileId", nativeQuery = true)
    BigDecimal getAccountsBalance(Long profileId);
}
