package com.crimsonhub.CrimsonFinanceAPI.domain.type;

/**
 * Enumeração que representa os diferentes tipos de contas financeiras.
 * <p>
 *     Esta enum é utilizada para categorizar as contas dentro do sistema financeiro,
 *     permitindo uma classificação consistente em operações e relatórios.
 * </p>
 *
 * <p><b>Tipos de conta disponíveis:</b></p>
 * <ul>
 *     <li>{@link #CURRENT_ACCOUNT} - Conta corrente.</li>
 *     <li>{@link #WALLET_ACCOUNT} - Conta de carteira digital.</li>
 *     <li>{@link #SAVINGS_ACCOUNT} - Conta poupança.</li>
 *     <li>{@link #INVESTMENT_ACCOUNT} - Conta de investimento.</li>
 *     <li>{@link #OTHER} - Outros tipos de contas não especificadas.</li>
 * </ul>
 *
 * @author Crimson Solutions
 * @version 1.0
 * @since 2024-01-01
 */
public enum AccountType {

    /**
     * Conta corrente.
     * <p>
     * Utilizada para movimentações financeiras comuns, como pagamentos e transferências.
     * </p>
     */
    CURRENT_ACCOUNT,

    /**
     * Conta de carteira digital.
     * <p>
     * Representa contas digitais usadas para transações rápidas, como pagamentos online.
     * </p>
     */
    WALLET_ACCOUNT,

    /**
     * Conta poupança.
     * <p>
     * Utilizada para guardar dinheiro com foco em segurança e rendimento limitado.
     * </p>
     */
    SAVINGS_ACCOUNT,

    /**
     * Conta de investimento.
     * <p>
     * Usada para aplicar recursos financeiros em ativos como ações, fundos ou outros produtos financeiros.
     * </p>
     */
    INVESTMENT_ACCOUNT,

    /**
     * Outros tipos de contas.
     * <p>
     * Categoria genérica para tipos de contas não especificados ou personalizados.
     * </p>
     */
    OTHER
}
