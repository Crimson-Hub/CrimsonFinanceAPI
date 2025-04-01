package com.crimsonhub.CrimsonFinanceAPI.exception;

/**
 * Exceção personalizada lançada quando uma senha incorreta é informada.
 * <p>
 *     Esta exceção é usada para sinalizar que uma operação envolvendo um perfil de usuário falhou
 *     devido à senha informada divergente da senha armazenada no sistema.
 * </p>
 *
 * <p><b>Exemplo de uso:</b></p>
 * <pre>
 *     throw new InvalidPasswordException(123L);
 * </pre>
 *
 * @see RuntimeException
 * @author Crimson Solutions
 * @version 1.0
 * @since 2024-01-01
 */
public class InvalidPasswordException extends RuntimeException {

    /**
     * Construtor da exceção que aceita o ID do perfil de usuário ao qual a senha informada está incorreta.
     *
     * @param id O identificador único do perfil de usuário informado.
     */
    public InvalidPasswordException(Long id) {
        super("Access denied with ID: " + id, null, false, false);
    }
}