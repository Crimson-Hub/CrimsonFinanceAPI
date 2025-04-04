package com.crimsonhub.CrimsonFinanceAPI.controller;

import jakarta.validation.Valid;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.card.CardAssignDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.card.CardResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.card.CardUpdateDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.card.InvoiceAssignDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.entity.Invoice;
import com.crimsonhub.CrimsonFinanceAPI.domain.entity.Transaction;
import com.crimsonhub.CrimsonFinanceAPI.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Controlador responsável por gerenciar operações relacionadas a cartões.
 * <p>
 *     Este controlador expõe endpoints REST para associar, atualizar, excluir cartões,
 *     e recuperar informações como transações e faturas associadas.
 * </p>
 *
 * @author Crimson Finance
 * @version 1.0
 * @since 2025-01-01
 */
@RestController
@RequestMapping("api/card")
public class CardController {

    @Autowired
    private CardService cardService;

    /**
     * Associa um novo cartão a um perfil específico.
     *
     * @param profileId ID do perfil ao qual o cartão será associado.
     * @param data Objeto {@link CardAssignDTO} contendo os detalhes do cartão.
     * @return Resposta com status HTTP 201 (Created) em caso de sucesso.
     */
    @PostMapping(
            value = "/assign",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            headers = {"Content-Type=application/json", "Accept=application/json"}
    )
    public ResponseEntity<?> assignCard(@RequestParam Long profileId, @Valid @RequestBody CardAssignDTO data) {
        cardService.assignCard(profileId, data);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Atualiza os detalhes de um cartão existente.
     *
     * @param id ID do cartão a ser atualizado.
     * @param data Objeto {@link CardUpdateDTO} contendo as informações atualizadas.
     * @return Resposta com status HTTP 200 (OK) em caso de sucesso.
     */
    @PostMapping(
            value = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            headers = {"Content-Type=application/json", "Accept=application/json"}
    )
    public ResponseEntity<?> updateCard(@RequestParam Long id, @Valid @RequestBody CardUpdateDTO data) {
        cardService.updateCard(id, data);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Exclui um cartão existente.
     *
     * @param id ID do cartão a ser excluído.
     * @return Resposta com status HTTP 200 (OK) em caso de sucesso.
     */
    @DeleteMapping(
            value = "/delete"
    )
    public ResponseEntity<?> deleteCard(@RequestParam Long id) {
        cardService.deleteCard(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Retorna todos os cartões associados a um perfil específico.
     *
     * @param profileId ID do perfil cujos cartões devem ser recuperados.
     * @return Resposta contendo um conjunto de {@link CardResponseDTO} e status HTTP 200 (OK).
     */
    @GetMapping(
            value = "/cards",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> accounts(@RequestParam Long profileId) {
        Set<CardResponseDTO> response = cardService.cards(profileId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(
            value = "/cardsBalance",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> cardsBalance(@RequestParam Long profileId) {
        String response = cardService.cardsBalance(profileId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Retorna todas as transações associadas a um cartão específico.
     *
     * @param id ID do cartão cujas transações devem ser recuperadas.
     * @return Resposta contendo um conjunto de {@link Transaction} e status HTTP 200 (OK).
     */
    @GetMapping(
            value = "/transactions",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> transactions(@RequestParam Long id) {
        Set<Transaction> response = cardService.transactions(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Associa uma nova fatura a um cartão existente.
     *
     * @param id ID do cartão ao qual a fatura será associada.
     * @param data Objeto {@link InvoiceAssignDTO} contendo os detalhes da fatura.
     * @return Resposta com status HTTP 201 (Created) em caso de sucesso.
     */
    @PostMapping(
            value = "invoice/assign",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            headers = {"Content-Type=application/json", "Accept=application/json"}
    )
    public ResponseEntity<?> assignInvoice(@RequestParam Long id, @Valid @RequestBody InvoiceAssignDTO data) {
        cardService.assignInvoice(id, data);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Retorna todas as faturas associadas a um cartão em um mês e ano específicos.
     *
     * @param id ID do cartão cujas faturas devem ser recuperadas.
     * @param month Mês para filtrar as faturas.
     * @param year Ano para filtrar as faturas.
     * @return Resposta contendo um conjunto de {@link Invoice} e status HTTP 200 (OK).
     */
    @GetMapping(
            value = "/invoices",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> invoices(@RequestParam Long id, @RequestParam int month, @RequestParam int year) {
        Set<Invoice> response = cardService.invoices(id, month, year);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
