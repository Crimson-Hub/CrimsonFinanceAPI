package com.crimsonhub.CrimsonFinanceAPI.service;

import com.crimsonhub.CrimsonFinanceAPI.domain.dto.account.AccountCreateDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.account.AccountResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.account.AccountUpdateDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.entity.Account;
import com.crimsonhub.CrimsonFinanceAPI.domain.entity.Profile;
import com.crimsonhub.CrimsonFinanceAPI.domain.entity.Transaction;
import com.crimsonhub.CrimsonFinanceAPI.domain.type.AccountType;
import com.crimsonhub.CrimsonFinanceAPI.exception.AccountNotFoundException;
import com.crimsonhub.CrimsonFinanceAPI.exception.ProfileNotFoundException;
import com.crimsonhub.CrimsonFinanceAPI.repository.AccountRepository;
import com.crimsonhub.CrimsonFinanceAPI.repository.ProfileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Set;

/**
 * Serviço responsável pela gestão de contas no sistema.
 * <p>
 *     Esta classe contém a lógica para criar, atualizar, excluir e recuperar contas e transações associadas a um perfil.
 * </p>
 *
 * @author Crimson Finance
 * @version 1.0
 * @since 2025-01-01
 */
@Service
public class AccountService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Cria uma conta associada a um perfil existente.
     *
     * @param profileId O ID do perfil ao qual a conta será associada.
     * @param data Objeto {@link AccountCreateDTO} contendo os dados da nova conta.
     * @throws ProfileNotFoundException Se o perfil com o ID fornecido não for encontrado.
     */
    public void createAccount(Long profileId, AccountCreateDTO data) {
        Profile profileEntity = profileRepository.findById(profileId)
                .orElseThrow(() -> new ProfileNotFoundException(profileId));

        Account accountEntity = modelMapper.map(data, Account.class);
        accountEntity.setProfile(profileEntity);
        accountEntity.setAccountType(AccountType.valueOf(data.type()));
        accountEntity.setCurrentBalance(data.initialBalance());

        accountRepository.save(accountEntity);
    }

    /**
     * Atualiza os dados de uma conta existente.
     *
     * @param id O ID da conta que será atualizada.
     * @param data Objeto {@link AccountUpdateDTO} contendo os novos dados da conta.
     * @throws AccountNotFoundException Se a conta com o ID fornecido não for encontrada.
     */
    public void updateAccount(Long id, AccountUpdateDTO data) {
        accountRepository.findById(id)
                .map(accountEntity -> {

                    modelMapper.map(data, accountEntity);

                    return accountRepository.save(accountEntity);
                })
                .orElseThrow(() -> new AccountNotFoundException(id));
    }

    /**
     * Retorna um conjunto de contas associadas a um perfil.
     *
     * @param profileId O ID do perfil cujas contas serão retornadas.
     * @return Um conjunto de objetos {@link AccountResponseDTO}.
     */
    public Set<AccountResponseDTO> accounts(Long profileId) {
        return accountRepository.findAccountsByProfileId(profileId);
    }

    /**
     * Retorna as transações associadas a uma conta.
     *
     * @param id O ID da conta cujas transações serão retornadas.
     * @return Um conjunto de objetos {@link Transaction}.
     */
    public Set<Transaction> transactions(Long id) {
        return accountRepository.findTransactionsByAccountId(id);
    }

    public String accountsBalance(Long profileId) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
        return decimalFormat.format(accountRepository.getAccountsBalance(profileId));
    }

    /**
     * Exclui uma conta pelo ID.
     *
     * @param id O ID da conta que será excluída.
     */
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}
