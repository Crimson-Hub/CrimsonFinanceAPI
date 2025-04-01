package com.crimsonhub.CrimsonFinanceAPI.domain.entity;

import com.crimsonhub.CrimsonFinanceAPI.domain.type.AccountType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Entidade que representa uma conta financeira no sistema.
 * <p>
 *     Esta classe é mapeada para a tabela <code>account</code> no banco de dados e contém informações detalhadas
 *     sobre o saldo, o tipo de conta, a empresa associada e o histórico de transações.
 * </p>
 *
 * <p><b>Campos principais:</b></p>
 * <ul>
 *     <li>{@link #id}: Identificador único da conta.</li>
 *     <li>{@link #profile}: Relacionamento com o titular da conta ({@link Profile}).</li>
 *     <li>{@link #initialBalance}: Saldo inicial da conta.</li>
 *     <li>{@link #currentBalance}: Saldo atual da conta.</li>
 *     <li>{@link #company}: Nome da instituição ou empresa associada à conta.</li>
 *     <li>{@link #accountType}: Tipo de conta ({@link AccountType}).</li>
 *     <li>{@link #transactions}: Conjunto de transações associadas à conta.</li>
 * </ul>
 *
 * <p><b>Annotations:</b></p>
 * <ul>
 *     <li>{@link Entity}: Define a classe como uma entidade JPA.</li>
 *     <li>{@link Table}: Especifica a tabela no banco de dados como <code>account</code>.</li>
 *     <li>{@link Id}: Indica a chave primária da entidade.</li>
 *     <li>{@link GeneratedValue}: Configura a estratégia de geração do identificador.</li>
 *     <li>{@link ManyToOne}: Configura o relacionamento com a entidade {@link Profile}.</li>
 *     <li>{@link OneToMany}: Configura o relacionamento com a entidade {@link Transaction}.</li>
 * </ul>
 *
 * <p><b>Lombok Annotations:</b></p>
 * <ul>
 *     <li>{@link Builder}: Permite a construção de objetos de maneira fluida.</li>
 *     <li>{@link Data}: Gera automaticamente métodos <code>getters</code>, <code>setters</code>, <code>toString</code>, <code>hashCode</code>, e <code>equals</code>.</li>
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
@Table(name = "account")
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
public class Account {

    /**
     * Identificador único da conta.
     * Gerado automaticamente pela estratégia de incremento definida.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Titular da conta.
     * <p>
     * Relacionamento com a entidade {@link Profile}, utilizando o <code>id_profile</code> como chave estrangeira.
     * Este campo é obrigatório.
     * </p>
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profile", nullable = false)
    private Profile profile;

    /**
     * Saldo inicial da conta.
     * <p>
     * Configurado com precisão de até 12 dígitos e 2 casas decimais.
     * Este campo é obrigatório.
     * </p>
     */
    @Column(name = "initial_balance", precision = 12, scale = 2, nullable = false)
    private BigDecimal initialBalance;

    /**
     * Saldo atual da conta.
     * <p>
     * Configurado com precisão de até 12 dígitos e 2 casas decimais.
     * Este campo é obrigatório.
     * </p>
     */
    @Column(name = "current_balance", precision = 12, scale = 2, nullable = false)
    private BigDecimal currentBalance;

    /**
     * Nome da instituição ou empresa associada à conta.
     * <p>
     * Este campo é obrigatório e possui limite de 30 caracteres.
     * </p>
     */
    @Column(name = "company", nullable = false, length = 30)
    private String company;

    /**
     * Tipo da conta.
     * <p>
     * Valor do tipo {@link AccountType}, indicando a natureza da conta (corrente, poupança, etc.).
     * Este campo é obrigatório.
     * </p>
     */
    @Column(name = "type", nullable = false)
    private AccountType accountType;

    /**
     * Quantidade total de despesas realizadas pela conta.
     * Inicializado com o valor 0.
     */
    @Column(name = "amount_expenses")
    private int amountExpenses = 0;

    /**
     * Quantidade total de receitas registradas na conta.
     * Inicializado com o valor 0.
     */
    @Column(name = "amount_revenues")
    private int amountRevenues = 0;

    /**
     * Quantidade total de transferências associadas à conta.
     * Inicializado com o valor 0.
     */
    @Column(name = "amount_transfers")
    private int amountTransfers = 0;

    /**
     * Indica se a conta deve ser exibida na tela inicial.
     * <p>
     * Por padrão, o valor é <code>false</code>.
     * </p>
     */
    @Column(name = "home_screen", nullable = false)
    private boolean homeScreen = false;

    /**
     * Data de criação da conta.
     * <p>
     * Utiliza o tipo {@link Timestamp} e é obrigatório.
     * </p>
     */
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdAt;

    /**
     * Transações associadas à conta.
     * <p>
     * Relacionamento <code>@OneToMany</code> com a entidade {@link Transaction}, utilizando o <code>id_account</code> como chave estrangeira.
     * </p>
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_account", unique = true)
    private Set<Transaction> transactions;
}

