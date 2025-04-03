package com.crimsonhub.CrimsonFinanceAPI.controller;

import com.crimsonhub.CrimsonFinanceAPI.domain.dto.transaction.TransactionResponseDTO;
import jakarta.validation.Valid;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.transaction.TransactionCreateDTO;
import com.crimsonhub.CrimsonFinanceAPI.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Controlador responsável por gerenciar operações relacionadas a transações financeiras.
 * <p>
 * Este controlador expõe endpoints REST para transferências, despesas com cartão
 * e exclusão de transações.
 * </p>
 *
 * @author Crimson Finance
 * @version 1.0
 * @since 2025-01-01
 */
@RestController
@RequestMapping("api/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    /**
     * Realiza uma transferência entre contas.
     *
     * @param accountId ID da conta de origem.
     * @param data Objeto {@link TransactionCreateDTO} contendo os detalhes da transação.
     * @return Resposta com status HTTP 201 (Created) em caso de sucesso.
     */
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            headers = {"Content-Type=application/json", "Accept=application/json"}
    )
    public ResponseEntity<?> expense(@RequestParam Long accountId, @Valid @RequestBody TransactionCreateDTO data) {
        transactionService.executeTransaction(accountId, data);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Registra uma despesa utilizando cartão.
     *
     * @param cardId ID do cartão utilizado na transação.
     * @param data Objeto {@link TransactionCreateDTO} contendo os detalhes da despesa.
     * @return Resposta com status HTTP 201 (Created) em caso de sucesso.
     */
    @PostMapping(
            value = "/cardExpense",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            headers = {"Content-Type=application/json", "Accept=application/json"}
    )
    public ResponseEntity<?> cardExpense(@RequestParam Long cardId, @Valid @RequestBody TransactionCreateDTO data) {
        transactionService.executeCardTransaction(cardId, data);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Endpoint para obter o saldo das transações de um determinado tipo.
     *
     * <p>Este metodo recebe um tipo de transação e o ID da conta ou cartão e retorna o saldo correspondente.
     * O tipo de transação pode indicar se a consulta é para cartões de crédito, receitas ou despesas.</p>
     *
     * @param type      O tipo de transação (exemplo: "credit", "revenue", "expense").
     * @param targetId O ID da conta para a qual o saldo será consultado.
     * @return ResponseEntity contendo o saldo calculado no formato JSON.
     */
    @GetMapping(
            value = "/balances/{type}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            headers = {"Content-Type=application/json", "Accept=application/json"}
    )
    public ResponseEntity<?> transactionBalances(@PathVariable String type, @RequestParam Long targetId) {
        String response = transactionService.transactionBalances(type, targetId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Endpoint para obter os principais saldos das transações de um determinado tipo.
     *
     * <p>
     *   Este metodo recebe um tipo de transação e o ID da conta e retorna os saldos correspondentes.
     *   O tipo de transação pode indicar se a consulta é para receitas ou despesas.
     * </p>
     *
     * @param type      O tipo de transação (exemplo: "revenue", "expense").
     * @param targetId  O ID da conta para a qual o saldo será consultado.
     * @return ResponseEntity contendo a lista dos principais saldos no formato JSON.
     */
    @GetMapping(
            value = "/top-balances/{type}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            headers = {"Content-Type=application/json", "Accept=application/json"}
    )
    public ResponseEntity<?> transactionTopBalances(@PathVariable String type, @RequestParam Long targetId) {
        Set<TransactionResponseDTO> response = transactionService.transactionTopBalances(type, targetId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Exclui uma transação específica.
     *
     * @param id ID da transação a ser excluída.
     * @return Resposta com status HTTP 200 (OK) em caso de sucesso.
     */
    @DeleteMapping(
            value = "/delete"
    )
    public ResponseEntity<?> delete(@RequestParam Long id) {
        transactionService.deleteTransaction(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
