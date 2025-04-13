package com.crimsonhub.CrimsonFinanceAPI.service;

import com.crimsonhub.CrimsonFinanceAPI.domain.dto.transaction.TransactionInsertDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.transaction.TransactionResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.entity.CardTransaction;
import com.crimsonhub.CrimsonFinanceAPI.exception.CardNotFoundException;
import com.crimsonhub.CrimsonFinanceAPI.repository.CardRepository;
import com.crimsonhub.CrimsonFinanceAPI.repository.CardTransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CardTransactionService {

    @Autowired
    private CardTransactionRepository cardTransactionRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ModelMapper modelMapper;

    public void insertCardTransaction(Long cardId, TransactionInsertDTO data) {
        cardRepository.findById(cardId).map(cardEntity -> {

            CardTransaction cardTransaction = modelMapper.map(data, CardTransaction.class);
            cardTransaction.setProfile(cardTransaction.getProfile());
            cardTransaction.setCard(cardEntity);

            return cardTransactionRepository.save(cardTransaction);
        })
                .orElseThrow(() -> new CardNotFoundException(cardId));
    }

    public BigDecimal getTotalExpensesForCard(Long profileId) {
        return cardTransactionRepository.getTotalExpensesForCard(profileId);
    }

    public List<TransactionResponseDTO> findTransactionsByCardId(Long cardId) {
        return cardTransactionRepository.findTransactionsByCardId(cardId);
    }

    public void deleteCardTransaction(Long transactionId) {
        cardTransactionRepository.deleteById(transactionId);
    }
}
