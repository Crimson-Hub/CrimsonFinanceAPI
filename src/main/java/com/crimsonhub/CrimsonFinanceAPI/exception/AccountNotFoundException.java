package com.crimsonhub.CrimsonFinanceAPI.exception;

/**
 * Exceção personalizada lançada quando uma conta não é encontrada no sistema.
 * <p>
 *     Esta exceção é usada para sinalizar que uma operação envolvendo uma conta falhou
 *     devido à ausência de uma conta com o identificador especificado.
 * </p>
 *
 * <p><b>Exemplo de uso:</b></p>
 * <pre>
 *     throw new AccountNotFoundException(123L);
 * </pre>
 *
 * @see RuntimeException
 * @author Crimson Solutions
 * @version 1.0
 * @since 2024-01-01
 */
public class AccountNotFoundException extends RuntimeException {

    /**
     * Construtor da exceção que aceita o ID da conta não encontrada.
     *
     * @param id O identificador único da conta que não foi encontrada.
     */
    public AccountNotFoundException(Long id) {
        super("Account not found with ID: " + id, null, false, false);
    }
}
