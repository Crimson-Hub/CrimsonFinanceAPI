package com.crimsonhub.CrimsonFinanceAPI.exception;

/**
 * Exceção personalizada lançada quando um perfil de usuário não é encontrado no sistema.
 * <p>
 *     Esta exceção é usada para sinalizar que uma operação envolvendo um perfil de usuário falhou
 *     devido à ausência de um perfil de usuário com o identificador especificado.
 * </p>
 *
 * <p><b>Exemplo de uso:</b></p>
 * <pre>
 *     throw new ProfileNotFoundException(123L);
 * </pre>
 *
 * @see RuntimeException
 * @author Crimson Solutions
 * @version 1.0
 * @since 2024-01-01
 */
public class ProfileNotFoundException extends RuntimeException {

    /**
     * Construtor da exceção que aceita o ID do perfil de usuário não encontrado.
     *
     * @param id O identificador único do perfil de usuário que não foi encontrado.
     */
    public ProfileNotFoundException(Object id) {
        super("Profile not found with ID: " + id, null, false, false);
    }
}
