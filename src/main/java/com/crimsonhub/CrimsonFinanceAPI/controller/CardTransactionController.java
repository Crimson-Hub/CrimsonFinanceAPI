package com.crimsonhub.CrimsonFinanceAPI.controller;

import com.crimsonhub.CrimsonFinanceAPI.domain.dto.transaction.TransactionInsertDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.transaction.TransactionResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.service.CardTransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/cards/transactions")
public class CardTransactionController {

    @Autowired
    private CardTransactionService cardTransactionService;

    @PostMapping("/{cardId}")
    public ResponseEntity<?> insertCardTransaction(@PathVariable Long cardId, @Valid @RequestBody TransactionInsertDTO data) {
        cardTransactionService.insertCardTransaction(cardId, data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{profileId}/total")
    public ResponseEntity<BigDecimal> getTotalExpensesForCard(@PathVariable Long profileId) {
        BigDecimal response = cardTransactionService.getTotalExpensesForCard(profileId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{cardId}")
    public ResponseEntity<List<TransactionResponseDTO>> findTransactionsByCardId(@PathVariable Long cardId) {
        List<TransactionResponseDTO> response = cardTransactionService.findTransactionsByCardId(cardId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<?> delete(@PathVariable Long transactionId) {
        cardTransactionService.deleteCardTransaction(transactionId);
        return ResponseEntity.noContent().build();
    }
}
