package com.crimsonhub.CrimsonFinanceAPI.controller;

import com.crimsonhub.CrimsonFinanceAPI.domain.dto.transaction.TransactionInsertDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.transaction.TransactionResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.transaction.TransactionTopResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.service.AccountTransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/accounts/transactions")
public class AccountTransactionController {

    @Autowired
    private AccountTransactionService accountTransactionService;

    @PostMapping("/{accountId}")
    public ResponseEntity<?> insertAccountTransaction(@PathVariable Long accountId, @Valid @RequestBody TransactionInsertDTO data) {
        accountTransactionService.insertAccountTransaction(accountId, data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{profileId}/{transactionTypeId}/total")
    public ResponseEntity<BigDecimal> getTotalAmountByTransactionType(@PathVariable Long profileId, @PathVariable Long transactionTypeId) {
        BigDecimal response = accountTransactionService.getTotalAmountByTransactionType(profileId, transactionTypeId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{profileId}/{transactionTypeId}/top")
    public ResponseEntity<List<TransactionTopResponseDTO>> findTopTransactionsByType(@PathVariable Long profileId, @PathVariable Long transactionTypeId) {
        List<TransactionTopResponseDTO> response = accountTransactionService.findTopTransactionsByType(profileId, transactionTypeId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<List<TransactionResponseDTO>> findTransactionsByAccountId(@PathVariable Long accountId) {
        List<TransactionResponseDTO> response = accountTransactionService.findTransactionsByAccountId(accountId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<?> delete(@PathVariable Long transactionId) {
        accountTransactionService.deleteAccountTransaction(transactionId);
        return ResponseEntity.noContent().build();
    }
}
