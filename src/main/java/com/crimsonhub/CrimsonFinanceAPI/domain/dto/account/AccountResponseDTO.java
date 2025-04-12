package com.crimsonhub.CrimsonFinanceAPI.domain.dto.account;

import java.math.BigDecimal;
import java.util.Date;

/**
 * DTO para resposta de dados de uma conta financeira.
 * <p>
 * Esta classe representa os dados detalhados de uma conta, que são retornados em operações de consulta
 * ou listagem no sistema.
 * </p>
 *
 * <p><b>Atributos:</b></p>
 * <ul>
 *     <li><b>id</b>: Identificador único da conta.</li>
 *     <li><b>initialBalance</b>: Saldo inicial da conta no momento da criação.</li>
 *     <li><b>currentBalance</b>: Saldo atual da conta.</li>
 *     <li><b>company</b>: Nome da instituição ou empresa associada à conta.</li>
 *     <li><b>type</b>: Tipo da conta, representado pelo enum {@link AccountType}.</li>
 *     <li><b>expenses</b>: Número de transações de despesa associadas à conta.</li>
 *     <li><b>revenues</b>: Número de transações de receita associadas à conta.</li>
 *     <li><b>transfers</b>: Número de transações de transferência associadas à conta.</li>
 *     <li><b>homeScreen</b>: Indica se a conta está configurada para exibição na tela inicial.</li>
 *     <li><b>createdAt</b>: Data de criação da conta.</li>
 * </ul>
 *
 * @param id Identificador único da conta.
 * @param initialBalance Saldo inicial da conta no momento da criação.
 * @param currentBalance Saldo atual da conta.
 * @param company Nome da instituição ou empresa associada à conta.
 * @param type Tipo da conta, representado pelo enum {@link AccountType}.
 * @param expenses Número de transações de despesa associadas à conta.
 * @param revenues Número de transações de receita associadas à conta.
 * @param transfers Número de transações de transferência associadas à conta.
 * @param homeScreen Indica se a conta está configurada para exibição na tela inicial.
 * @param createdAt Data de criação da conta.
 *
 * @author Crimson Solutions
 * @version 1.0
 * @since 2024-01-01
 */
public record AccountResponseDTO(Long id,
                                 BigDecimal initialBalance,
                                 BigDecimal currentBalance,
                                 String company,
                                 AccountType type,
                                 int expenses,
                                 int revenues,
                                 int transfers,
                                 boolean homeScreen,
                                 Date createdAt) {
}
