package com.crimsonhub.CrimsonFinanceAPI.repository;

import com.crimsonhub.CrimsonFinanceAPI.domain.dto.card.CardResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.entity.Card;
import com.crimsonhub.CrimsonFinanceAPI.domain.entity.Invoice;
import com.crimsonhub.CrimsonFinanceAPI.domain.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Repositório para a entidade {@link Card}.
 * <p>
 * Esta interface gerencia as operações de persistência para a entidade {@link Card}.
 * Ela herda de {@link JpaRepository}, fornecendo métodos padrão para CRUD, além de consultas personalizadas
 * com o uso da anotação {@link Query}.
 * </p>
 *
 * <p><b>Consultas Personalizadas:</b></p>
 * <ul>
 *     <li><b>findCardsByProfileId</b>: Retorna uma lista de {@link CardResponseDTO} com informações sobre os cartões associados ao perfil, baseado no ID do perfil.</li>
 *     <li><b>findAllTransactionsByCardId</b>: Retorna um conjunto de transações associadas a um cartão específico, baseado no ID do cartão.</li>
 *     <li><b>findAllInvoicesInMonthByCardId</b>: Retorna um conjunto de faturas associadas a um cartão, filtrando por mês e ano, com base no ID do cartão.</li>
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
public interface CardRepository extends JpaRepository<Card, Long> {

    /**
     * Retorna uma lista de {@link CardResponseDTO} contendo informações sobre os cartões associados ao ID de um perfil.
     *
     * @param profileId ID do perfil ao qual os cartões estão associados.
     * @return Lista de {@link CardResponseDTO} com informações dos cartões associados ao perfil.
     */
    @Query("SELECT new com.crimsonhub.CrimsonFinanceAPI.domain.dto.card.CardResponseDTO(" +
            "c.id, c.expiryDay, c.creditLimit" +
            ") FROM Card c WHERE c.profile.id = :profileId")
    Set<CardResponseDTO> findCardsByProfileId(Long profileId);

    /**
     * Retorna um conjunto de {@link Transaction} associadas a um cartão, identificado pelo seu ID.
     *
     * @param id ID do cartão do qual as transações serão recuperadas.
     * @return Conjunto de {@link Transaction} associadas ao cartão.
     */
    @Query("SELECT c.transactions FROM Card c WHERE c.id = :id")
    Set<Transaction> findTransactionsByCardId(Long id);

    /**
     * Retorna um conjunto de {@link Invoice} associadas a um cartão, filtrando por mês e ano de vencimento da fatura.
     *
     * @param id ID do cartão do qual as faturas serão recuperadas.
     * @param month Mês de vencimento da fatura.
     * @param year Ano de vencimento da fatura.
     * @return Conjunto de {@link Invoice} associadas ao cartão e filtradas por mês e ano de vencimento.
     */
    @Query("SELECT i FROM Card c JOIN c.invoices i WHERE c.id = :id " +
            "AND MONTH(i.dateDue) = :month " +
            "AND YEAR(i.dateDue) = :year")
    Set<Invoice> findInvoicesInMonthByCardId(Long id, int month, int year);

    @Query("SELECT c.currentExpenses FROM Card c WHERE c.profile.id = :profileId")
    String getCardsBalance(Long profileId);
}
