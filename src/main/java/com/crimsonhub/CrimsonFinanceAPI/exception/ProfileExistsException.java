package com.crimsonhub.CrimsonFinanceAPI.exception;

/**
 * Exceção personalizada para indicar que um perfil já existe.
 * <p>
 * Essa exceção é lançada quando há uma tentativa de criar ou registrar
 * um perfil com um identificador já existente no sistema.
 * </p>
 *
 * @see RuntimeException
 * @author Crimson Finance
 * @version 1.0
 * @since 2025-01-01
 */
public class ProfileExistsException extends RuntimeException {

    /**
     * Construtor para a exceção ProfileExistsException.
     *
     * @param id O identificador associado ao perfil que já existe.
     *           Ele pode ser um objeto que represente um ID único (ex.: Long ou String).
     */
    public ProfileExistsException(Object id) {
        super("Profile already exists with ID: " + id, null, false, false);
    }
}
