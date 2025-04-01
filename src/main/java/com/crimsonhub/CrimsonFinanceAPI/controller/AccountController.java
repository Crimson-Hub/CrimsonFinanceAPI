package com.crimsonhub.CrimsonFinanceAPI.controller;

import com.crimsonhub.CrimsonFinanceAPI.domain.dto.account.AccountsBalanceResponseDTO;
import jakarta.validation.Valid;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.account.AccountCreateDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.account.AccountResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.account.AccountUpdateDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.entity.Transaction;
import com.crimsonhub.CrimsonFinanceAPI.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Controlador responsável por gerenciar operações relacionadas a contas.
 * <p>
 *     Este controlador expõe endpoints REST para criar, atualizar, excluir
 *     contas e recuperar informações como transações associadas.
 * </p>
 *
 * @author Crimson Finance
 * @version 1.0
 * @since 2025-01-01
 */
@RestController
@RequestMapping("api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * Cria uma conta associada a um perfil específico.
     *
     * @param profileId ID do perfil ao qual a conta será associada.
     * @param data Objeto {@link AccountCreateDTO} contendo os detalhes da conta a ser criada.
     * @return Resposta com status HTTP 201 (Created) em caso de sucesso.
     */
    @PostMapping(
            value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            headers = {"Content-Type=application/json", "Accept=application/json"}
    )
    public ResponseEntity<?> createAccount(@RequestParam Long profileId, @Valid @RequestBody AccountCreateDTO data) {
        accountService.createAccount(profileId, data);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Atualiza uma conta existente.
     *
     * @param id ID da conta a ser atualizada.
     * @param data Objeto {@link AccountUpdateDTO} contendo os detalhes da atualização.
     * @return Resposta com status HTTP 200 (OK) em caso de sucesso.
     */
    @PostMapping(
            value = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            headers = {"Content-Type=application/json", "Accept=application/json"}
    )
    public ResponseEntity<?> updateAccount(@RequestParam Long id, @Valid @RequestBody AccountUpdateDTO data) {
        accountService.updateAccount(id, data);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Exclui uma conta existente.
     *
     * @param id ID da conta a ser excluída.
     * @return Resposta com status HTTP 200 (OK) em caso de sucesso.
     */
    @DeleteMapping(
            value = "/delete"
    )
    public ResponseEntity<?> deleteAccount(@RequestParam Long id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Retorna todas as contas associadas a um perfil específico.
     *
     * @param profileId ID do perfil cujas contas devem ser recuperadas.
     * @return Resposta contendo um conjunto de {@link AccountResponseDTO} e status HTTP 200 (OK).
     */
    @GetMapping(
            value = "/accounts",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> accounts(@RequestParam Long profileId) {
        Set<AccountResponseDTO> response = accountService.accounts(profileId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(
            value = "/accountsBalance",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> accountsBalance(@RequestParam Long profileId) {
        String response = accountService.accountsBalance(profileId);
        return ResponseEntity.status(HttpStatus.OK).body(new AccountsBalanceResponseDTO(response));
    }

    /**
     * Retorna todas as transações associadas a uma conta específica.
     *
     * @param id ID da conta cujas transações devem ser recuperadas.
     * @return Resposta contendo um conjunto de {@link Transaction} e status HTTP 200 (OK).
     */
    @GetMapping(
            value = "/transactions",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> transactions(@RequestParam Long id) {
        Set<Transaction> response = accountService.transactions(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
