package com.crimsonhub.CrimsonFinanceAPI.service;

import com.crimsonhub.CrimsonFinanceAPI.domain.dto.transaction.TransactionCreateDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.transaction.TransactionResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.entity.odl.Transaction;
import com.crimsonhub.CrimsonFinanceAPI.exception.AccountNotFoundException;
import com.crimsonhub.CrimsonFinanceAPI.exception.CardNotFoundException;
import com.crimsonhub.CrimsonFinanceAPI.repository.AccountRepository;
import com.crimsonhub.CrimsonFinanceAPI.repository.CardRepository;
import com.crimsonhub.CrimsonFinanceAPI.repository.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

/**
 * Serviço responsável pela execução e gerenciamento de transações financeiras.
 * <p>
 * Esta classe fornece funcionalidades para executar transações em contas e cartões, bem como excluir transações.
 * </p>
 *
 * @author Crimson Finance
 * @version 1.0
 * @since 2025-01-01
 */
@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Executa uma transação em uma conta bancária.
     *
     * @param accountId O ID da conta na qual a transação será executada.
     * @param data Os dados da transação a ser realizada.
     * @throws AccountNotFoundException Se a conta com o ID especificado não for encontrada.
     */
    public void executeTransaction(Long accountId, TransactionCreateDTO data) {
        accountRepository.findById(accountId)
                .map(accountEntity -> {

                    Transaction transactionEntity = modelMapper.map(data, Transaction.class);
                    transactionEntity.setTransactionType(TransactionType.valueOf(data.type()));
                    accountEntity.getTransactions().add(transactionEntity);

                    return accountRepository.save(accountEntity);
                })
                .orElseThrow(() -> new AccountNotFoundException(accountId));
    }

    /**
     * Executa uma transação em um cartão de crédito.
     *
     * @param cardId O ID do cartão no qual a transação será executada.
     * @param data Os dados da transação a ser realizada.
     * @throws CardNotFoundException Se o cartão com o ID especificado não for encontrado.
     */
    public void executeCardTransaction(Long cardId, TransactionCreateDTO data) {
        cardRepository.findById(cardId)
                .map(cardEntity -> {

                    Transaction transactionEntity = modelMapper.map(data, Transaction.class);
                    transactionEntity.setTransactionType(TransactionType.CARD_EXPENSE);
                    cardEntity.getTransactions().add(transactionEntity);

                    return cardRepository.save(cardEntity);
                })
                .orElseThrow(() -> new CardNotFoundException(cardId));
    }

    /**
     * Retorna o saldo total das transações de um determinado tipo para um alvo específico.
     *
     * @param type      O tipo de transação (ex: "CARD_EXPENSE", "EXPENSE", "REVENUE").
     * @param targetId  O identificador do autor das transações.
     * @return O saldo total das transações como uma string.
     */
    public String transactionBalances(String type, Long targetId) {
        TransactionType transactionType = TransactionType.valueOf(type);
        return switch (transactionType) {
            case CARD_EXPENSE -> transactionRepository.getCardExpensesBalance(targetId);
            case EXPENSE -> transactionRepository.getExpensesBalance(targetId);
            case REVENUE -> transactionRepository.getRevenuesBalance(targetId);
            default -> "";
        };
    }

    /**
     * Retorna as transações de maior impacto (top) para um determinado tipo e alvo.
     *
     * @param type     O tipo de transação (ex: "EXPENSE", "REVENUE").
     * @param targetId O identificador do alvo das transações.
     * @return Um conjunto de objetos {@code TransactionResponseDTO} contendo as transações mais relevantes.
     */
    public Set<TransactionResponseDTO> transactionTopBalances(String type, Long targetId) {
        TransactionType transactionType = TransactionType.valueOf(type);
        return switch (transactionType) {
            case EXPENSE -> transactionRepository.getExpenseTransactionsTop(targetId);
            case REVENUE -> transactionRepository.getRevenueTransactionsTop(targetId);
            default -> Collections.emptySet();
        };
    }

    /**
     * Exclui uma transação com o ID especificado.
     *
     * @param id O ID da transação a ser excluída.
     */
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}
