package com.crimsonhub.CrimsonFinanceAPI.domain.type;

/**
 * Enumeração que representa os diferentes tipos de transações financeiras no sistema.
 * <p>
 * Utilizada para categorizar as transações de acordo com sua natureza, facilitando a organização,
 * análise e relatórios financeiros.
 * </p>
 *
 * <p><b>Tipos de transações disponíveis:</b></p>
 * <ul>
 *     <li>{@link #EXPENSE} - Despesa, representando uma saída de recursos.</li>
 *     <li>{@link #REVENUE} - Receita, representando uma entrada de recursos.</li>
 *     <li>{@link #TRANSFER} - Transferência, representando a movimentação entre contas.</li>
 *     <li>{@link #CARD_EXPENSE} - Despesa no cartão, representando gastos realizados com cartão de crédito ou débito.</li>
 * </ul>
 *
 * @author Crimson Solutions
 * @version 1.0
 * @since 2024-01-01
 */
public enum TransactionType {

    /**
     * Despesa.
     * <p>
     * Representa uma saída de recursos financeiros, como pagamentos, compras ou outros gastos.
     * </p>
     */
    EXPENSE,

    /**
     * Receita.
     * <p>
     * Representa uma entrada de recursos financeiros, como salários, vendas ou outros ganhos.
     * </p>
     */
    REVENUE,

    /**
     * Transferência.
     * <p>
     * Representa uma movimentação de recursos entre diferentes contas, sem alterar o saldo total.
     * </p>
     */
    TRANSFER,

    /**
     * Despesa no cartão.
     * <p>
     * Representa um gasto realizado com cartão de crédito ou débito, que pode ser categorizado separadamente
     * para controle e análise.
     * </p>
     */
    CARD_EXPENSE
}
