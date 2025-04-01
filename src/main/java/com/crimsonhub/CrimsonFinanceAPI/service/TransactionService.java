package com.crimsonhub.CrimsonFinanceAPI.service;

import com.crimsonhub.CrimsonFinanceAPI.domain.dto.transaction.TransactionCreateDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.transaction.TransactionResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.entity.Transaction;
import com.crimsonhub.CrimsonFinanceAPI.domain.type.TransactionType;
import com.crimsonhub.CrimsonFinanceAPI.exception.AccountNotFoundException;
import com.crimsonhub.CrimsonFinanceAPI.exception.CardNotFoundException;
import com.crimsonhub.CrimsonFinanceAPI.repository.AccountRepository;
import com.crimsonhub.CrimsonFinanceAPI.repository.CardRepository;
import com.crimsonhub.CrimsonFinanceAPI.repository.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
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

    public String transactionBalances(String type, Long accountId) {

    }

    public Set<TransactionResponseDTO> transactionTopBalances(String type, Long accountId) {

    }

    public String revenuesBalance(Long accountId) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
        return decimalFormat.format(transactionRepository.getRevenuesBalance(accountId));
    }

    public String expensesBalance(Long accountId) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
        return decimalFormat.format(transactionRepository.getExpensesBalance(accountId));
    }

    public String cardsExpensesBalance(Long accountId) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
        return decimalFormat.format(transactionRepository.getCardExpensesBalance(accountId));
    }

    public Set<TransactionResponseDTO> revenueTransactionsTop(Long accountId) {
        return transactionRepository.getRevenueTransactionsTop(accountId);
    }

    public Set<TransactionResponseDTO> expenseTransactionsTop(Long accountId) {
        return transactionRepository.getExpenseTransactionsTop(accountId);
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
