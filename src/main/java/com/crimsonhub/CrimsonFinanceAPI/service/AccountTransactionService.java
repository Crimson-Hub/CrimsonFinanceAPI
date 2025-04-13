package com.crimsonhub.CrimsonFinanceAPI.service;

import com.crimsonhub.CrimsonFinanceAPI.domain.dto.transaction.TransactionInsertDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.transaction.TransactionResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.transaction.TransactionTopResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.entity.AccountTransaction;
import com.crimsonhub.CrimsonFinanceAPI.exception.AccountNotFoundException;
import com.crimsonhub.CrimsonFinanceAPI.repository.AccountRepository;
import com.crimsonhub.CrimsonFinanceAPI.repository.AccountTransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountTransactionService {

    @Autowired
    private AccountTransactionRepository accountTransactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    public void insertAccountTransaction(Long accountId, TransactionInsertDTO data) {
        accountRepository.findById(accountId).map(accountEntity -> {

            AccountTransaction accountTransaction = modelMapper.map(data, AccountTransaction.class);
            accountTransaction.setProfile(accountTransaction.getProfile());
            accountTransaction.setAccount(accountEntity);

            return accountTransactionRepository.save(accountTransaction);
        })
                .orElseThrow(() -> new AccountNotFoundException(accountId));
    }

    public BigDecimal getTotalAmountByTransactionType(Long profileId, Long transactionTypeId) {
        return accountTransactionRepository.getTotalAmountByTransactionType(profileId, transactionTypeId);
    }

    public List<TransactionTopResponseDTO> findTopTransactionsByType(Long profileId, Long transactionTypeId) {
        return accountTransactionRepository.findTopTransactionsByType(profileId, transactionTypeId);
    }

    public List<TransactionResponseDTO> findTransactionsByAccountId(Long accountId) {
        return accountTransactionRepository.findTransactionsByAccountId(accountId);
    }

    public void deleteAccountTransaction(Long transactionId) {
        accountTransactionRepository.deleteById(transactionId);
    }
}
