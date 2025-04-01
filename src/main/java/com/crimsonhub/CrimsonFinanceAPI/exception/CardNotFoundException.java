package com.crimsonhub.CrimsonFinanceAPI.exception;

/**
 * Exceção personalizada lançada quando um cartão não é encontrado no sistema.
 * <p>
 *     Esta exceção é usada para sinalizar que uma operação envolvendo um cartão falhou
 *     devido à ausência de um cartão com o identificador especificado.
 * </p>
 *
 * <p><b>Exemplo de uso:</b></p>
 * <pre>
 *     throw new CardNotFoundException(123L);
 * </pre>
 *
 * @see RuntimeException
 * @author Crimson Solutions
 * @version 1.0
 * @since 2024-01-01
 */
public class CardNotFoundException extends RuntimeException {

    /**
     * Construtor da exceção que aceita o ID do cartão não encontrado.
     *
     * @param id O identificador único do cartão que não foi encontrado.
     */
    public CardNotFoundException(Long id) {
        super("Card not found with ID: " + id, null, false, false);
    }
}
