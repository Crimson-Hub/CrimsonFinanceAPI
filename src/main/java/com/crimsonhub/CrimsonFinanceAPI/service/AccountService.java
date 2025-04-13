package com.crimsonhub.CrimsonFinanceAPI.service;

import com.crimsonhub.CrimsonFinanceAPI.domain.dto.account.AccountCreateDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.account.AccountListResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.account.AccountUpdateDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.entity.Account;
import com.crimsonhub.CrimsonFinanceAPI.exception.AccountNotFoundException;
import com.crimsonhub.CrimsonFinanceAPI.exception.ProfileNotFoundException;
import com.crimsonhub.CrimsonFinanceAPI.repository.AccountRepository;
import com.crimsonhub.CrimsonFinanceAPI.repository.ProfileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    public void createAccount(Long profileId, AccountCreateDTO data) {
        profileRepository.findById(profileId).map(profileEntity -> {

            Account accountEntity = modelMapper.map(data, Account.class);
            accountEntity.setProfile(profileEntity);
            accountEntity.setCurrentBalance(data.initialBalance());

            return accountRepository.save(accountEntity);
        })
                .orElseThrow(() -> new ProfileNotFoundException(profileId));
    }

    public void updateAccount(Long id, AccountUpdateDTO data) {
        accountRepository.findById(id).map(accountEntity -> {

            modelMapper.map(data, accountEntity);

            return accountRepository.save(accountEntity);
        })
                .orElseThrow(() -> new AccountNotFoundException(id));
    }

    public List<AccountListResponseDTO> findAccountsByProfileId(Long profileId) {
        return accountRepository.findAccountsByProfileId(profileId);
    }

    public BigDecimal getTotalAccountsBalance(Long profileId) {
        return accountRepository.getTotalAccountsBalance(profileId);
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}
