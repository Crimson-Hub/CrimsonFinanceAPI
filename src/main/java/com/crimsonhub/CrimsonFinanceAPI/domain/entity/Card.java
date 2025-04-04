package com.crimsonhub.CrimsonFinanceAPI.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Entidade que representa um cartão financeiro no sistema.
 * <p>
 * Esta classe é mapeada para a tabela <code>card</code> no banco de dados e contém informações sobre o limite de crédito,
 * o titular do cartão, o dia de vencimento e as transações e faturas associadas.
 * </p>
 *
 * <p><b>Campos principais:</b></p>
 * <ul>
 *     <li>{@link #id}: Identificador único do cartão.</li>
 *     <li>{@link #profile}: Relacionamento com o titular do cartão ({@link Profile}).</li>
 *     <li>{@link #expiryDay}: Dia de vencimento do cartão.</li>
 *     <li>{@link #creditLimit}: Limite de crédito disponível no cartão.</li>
 *     <li>{@link #transactions}: Conjunto de transações realizadas com o cartão.</li>
 *     <li>{@link #invoices}: Conjunto de faturas associadas ao cartão.</li>
 * </ul>
 *
 * <p><b>Annotations:</b></p>
 * <ul>
 *     <li>{@link Entity}: Define a classe como uma entidade JPA.</li>
 *     <li>{@link Table}: Especifica a tabela no banco de dados como <code>card</code>.</li>
 *     <li>{@link Id}: Indica a chave primária da entidade.</li>
 *     <li>{@link GeneratedValue}: Configura a estratégia de geração do identificador.</li>
 *     <li>{@link ManyToOne}: Configura o relacionamento com a entidade {@link Profile}.</li>
 *     <li>{@link OneToMany}: Configura os relacionamentos com as entidades {@link Transaction} e {@link Invoice}.</li>
 * </ul>
 *
 * <p><b>Lombok Annotations:</b></p>
 * <ul>
 *     <li>{@link Builder}: Permite a construção de objetos de maneira fluida.</li>
 *     <li>{@link Data}: Gera automaticamente métodos <code>getters</code>, <code>setters</code>, <code>toString</code>,
 *     <code>hashCode</code>, e <code>equals</code>.</li>
 *     <li>{@link AllArgsConstructor}: Gera um construtor com todos os atributos.</li>
 *     <li>{@link RequiredArgsConstructor}: Gera um construtor com os atributos obrigatórios.</li>
 *     <li>{@link EqualsAndHashCode}: Define critérios de igualdade e cálculo de hash usando o atributo <code>id</code>.</li>
 * </ul>
 *
 * @author Crimson Solutions
 * @version 1.0
 * @since 2024-01-01
 */
@Entity
@Table(name = "card")
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
public class Card {

    /**
     * Identificador único do cartão.
     * Gerado automaticamente pela estratégia de incremento definida.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Titular do cartão.
     * <p>
     * Relacionamento com a entidade {@link Profile}, utilizando o <code>id_profile</code> como chave estrangeira.
     * Este campo é obrigatório.
     * </p>
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profile", nullable = false)
    private Profile profile;

    /**
     * Dia de vencimento do cartão.
     * Este campo é obrigatório e representa o dia do mês em que o pagamento é devido.
     */
    @Column(name = "expiry_day", nullable = false)
    private int expiryDay;

    /**
     * Limite de crédito do cartão.
     * <p>
     * Configurado com precisão de até 12 dígitos e 2 casas decimais.
     * Este campo é obrigatório.
     * </p>
     */
    @Column(name = "credit_limit", precision = 12, scale = 2, nullable = false)
    private BigDecimal creditLimit;

    /**
     * Limite de crédito do cartão já utilizado.
     * <p>
     *     Configurado com precisão ed até 12 dígitos e 2 casas decimais.
     *     Este campo é obrigatório.
     * </p>
     */
    @Column(name = "current_expenses", precision = 12, scale = 2, nullable = false)
    private BigDecimal currentExpenses = BigDecimal.ZERO;

    /**
     * Transações associadas ao cartão.
     * <p>
     * Relacionamento <code>@OneToMany</code> com a entidade {@link Transaction}, utilizando o <code>id_card</code> como chave estrangeira.
     * Este campo representa todas as transações feitas com o cartão.
     * </p>
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_card", unique = true)
    private Set<Transaction> transactions;

    /**
     * Faturas associadas ao cartão.
     * <p>
     * Relacionamento <code>@OneToMany</code> com a entidade {@link Invoice}, utilizando o <code>id_card</code> como chave estrangeira.
     * Este campo representa todas as faturas geradas para o cartão.
     * </p>
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_card", unique = true)
    private Set<Invoice> invoices;
}
