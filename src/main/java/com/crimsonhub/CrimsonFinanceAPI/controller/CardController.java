package com.crimsonhub.CrimsonFinanceAPI.controller;

import com.crimsonhub.CrimsonFinanceAPI.domain.dto.card.*;
import com.crimsonhub.CrimsonFinanceAPI.service.CardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping("/assign/{profileId}")
    public ResponseEntity<?> assignCard(@PathVariable Long profileId, @Valid @RequestBody CardAssignDTO data) {
        cardService.assignCard(profileId, data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCard(@PathVariable Long id, @Valid @RequestBody CardUpdateDTO data) {
        cardService.updateCard(id, data);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<CardListResponseDTO>> findCardsByProfileId(@RequestParam Long profileId) {
        List<CardListResponseDTO> response = cardService.findCardsByProfileId(profileId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/balance")
    public ResponseEntity<?> getTotalCardsBalance(@RequestParam Long profileId) {
        BigDecimal response = cardService.getTotalCardsBalance(profileId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{profileId}/top")
    public ResponseEntity<List<CardsDashboardResponseDTO>> findTopCardsByProfileId(@PathVariable Long profileId) {
        List<CardsDashboardResponseDTO> response = cardService.findTopCardsByProfileId(profileId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/invoiceAssign")
    public ResponseEntity<?> assignInvoice(@PathVariable Long id, @Valid @RequestBody InvoiceAssignDTO data) {
        cardService.assignInvoice(id, data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}/invoices")
    public ResponseEntity<List<InvoiceResponseDTO>> findInvoicesInMonthByCardId(@PathVariable Long id, @RequestParam int month, @RequestParam int year) {
        List<InvoiceResponseDTO> response = cardService.findInvoicesInMonthByCardId(id, month, year);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
        return ResponseEntity.noContent().build();
    }
}
