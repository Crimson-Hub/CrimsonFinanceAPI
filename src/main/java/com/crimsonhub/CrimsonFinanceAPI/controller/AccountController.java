package com.crimsonhub.CrimsonFinanceAPI.controller;

import com.crimsonhub.CrimsonFinanceAPI.domain.dto.account.AccountCreateDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.account.AccountListResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.account.AccountUpdateDTO;
import com.crimsonhub.CrimsonFinanceAPI.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/create/{profileId}")
    public ResponseEntity<?> createAccount(@PathVariable Long profileId, @Valid @RequestBody AccountCreateDTO data) {
        accountService.createAccount(profileId, data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable Long id, @Valid @RequestBody AccountUpdateDTO data) {
        accountService.updateAccount(id, data);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<AccountListResponseDTO>> findAccountsByProfileId(@RequestParam Long profileId) {
        List<AccountListResponseDTO> response = accountService.findAccountsByProfileId(profileId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/balance")
    public ResponseEntity<BigDecimal> getTotalAccountsBalance(@RequestParam Long profileId) {
        BigDecimal response = accountService.getTotalAccountsBalance(profileId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
