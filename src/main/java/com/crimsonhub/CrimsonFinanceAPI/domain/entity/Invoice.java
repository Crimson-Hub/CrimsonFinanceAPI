package com.crimsonhub.CrimsonFinanceAPI.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Entidade que representa uma fatura no sistema.
 * <p>
 * Esta classe é mapeada para a tabela <code>invoice</code> no banco de dados e contém informações
 * sobre o valor devido, a data de vencimento e o status de pagamento da fatura.
 * </p>
 *
 * <p><b>Campos principais:</b></p>
 * <ul>
 *     <li>{@link #id}: Identificador único da fatura.</li>
 *     <li>{@link #amountDue}: Valor devido da fatura.</li>
 *     <li>{@link #dateDue}: Data de vencimento da fatura.</li>
 *     <li>{@link #paid}: Status indicando se a fatura foi paga.</li>
 * </ul>
 *
 * <p><b>Annotations:</b></p>
 * <ul>
 *     <li>{@link Entity}: Define a classe como uma entidade JPA.</li>
 *     <li>{@link Table}: Especifica a tabela no banco de dados como <code>invoice</code>.</li>
 *     <li>{@link Id}: Indica a chave primária da entidade.</li>
 *     <li>{@link GeneratedValue}: Configura a estratégia de geração do identificador.</li>
 *     <li>{@link Column}: Configura as propriedades das colunas no banco de dados.</li>
 * </ul>
 *
 * <p><b>Lombok Annotations:</b></p>
 * <ul>
 *     <li>{@link Builder}: Permite a construção de objetos de maneira fluida.</li>
 *     <li>{@link Data}: Gera automaticamente métodos <code>getters</code>, <code>setters</code>, <code>toString</code>,
 *     <code>hashCode</code>, e <code>equals</code>.</li>
 *     <li>{@link AllArgsConstructor}: Gera um construtor com todos os atributos.</li>
 *     <li>{@link RequiredArgsConstructor}: Gera um construtor com os atributos finais obrigatórios.</li>
 *     <li>{@link EqualsAndHashCode}: Define critérios de igualdade e cálculo de hash usando o atributo <code>id</code>.</li>
 * </ul>
 *
 * @author Crimson Solutions
 * @version 1.0
 * @since 2024-01-01
 */
@Entity
@Table(name = "invoice")
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
public class Invoice {

    /**
     * Identificador único da fatura.
     * Gerado automaticamente pela estratégia de incremento definida.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Valor devido da fatura.
     * <p>
     * Configurado com precisão de até 12 dígitos e 2 casas decimais.
     * Este campo é obrigatório.
     * </p>
     */
    @Column(name = "amount_due", precision = 12, scale = 2, nullable = false)
    private BigDecimal amountDue;

    /**
     * Data de vencimento da fatura.
     * Este campo é obrigatório e representa o prazo final para o pagamento.
     */
    @Column(name = "date_due", nullable = false)
    private Date dateDue;

    /**
     * Status indicando se a fatura foi paga.
     * <p>
     * Por padrão, este campo é inicializado como <code>false</code>, indicando que a fatura não foi paga.
     * </p>
     */
    @Column(name = "paid", nullable = false)
    private boolean paid = false;
}
