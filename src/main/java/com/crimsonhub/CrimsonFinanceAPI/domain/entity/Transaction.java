package com.crimsonhub.CrimsonFinanceAPI.domain.entity;

import com.crimsonhub.CrimsonFinanceAPI.domain.type.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Entidade que representa uma transação financeira no sistema.
 * <p>
 * Esta classe é mapeada para a tabela <code>transaction</code> no banco de dados, armazenando informações
 * sobre valores, datas, descrições, categorias e tipos de transação.
 * </p>
 *
 * <p><b>Campos principais:</b></p>
 * <ul>
 *     <li>{@link #id}: Identificador único da transação.</li>
 *     <li>{@link #amount}: Valor monetário da transação.</li>
 *     <li>{@link #createdAt}: Data e hora de criação da transação.</li>
 *     <li>{@link #description}: Descrição breve da transação.</li>
 *     <li>{@link #category}: Categoria associada à transação.</li>
 *     <li>{@link #transactionType}: Tipo da transação, definido pela enumeração {@link TransactionType}.</li>
 * </ul>
 *
 * <p><b>Annotations:</b></p>
 * <ul>
 *     <li>{@link Entity}: Define a classe como uma entidade JPA.</li>
 *     <li>{@link Table}: Especifica a tabela no banco de dados como <code>transaction</code>.</li>
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
@Table(name = "transaction")
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {

    /**
     * Identificador único da transação.
     * Gerado automaticamente pela estratégia de incremento definida.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Valor monetário da transação.
     * <p>
     * Configurado com precisão de até 12 dígitos e 2 casas decimais.
     * Este campo é obrigatório.
     * </p>
     */
    @Column(name = "amount", precision = 12, scale = 2, nullable = false)
    private BigDecimal amount;

    /**
     * Data e hora de criação da transação.
     * Este campo é obrigatório e utiliza o tipo {@link Timestamp}.
     */
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdAt;

    /**
     * Descrição breve da transação.
     * <p>
     * Este campo é obrigatório e tem um limite de 40 caracteres.
     * </p>
     */
    @Column(name = "description", nullable = false, length = 40)
    private String description;

    /**
     * Categoria associada à transação.
     * <p>
     * Este campo é obrigatório e tem um limite de 20 caracteres.
     * </p>
     */
    @Column(name = "category", nullable = false, length = 20)
    private String category;

    /**
     * Tipo da transação.
     * <p>
     * Este campo é obrigatório e utiliza a enumeração {@link TransactionType},
     * que define os tipos possíveis de transações, como DESPESA, RECEITA, TRANSFERÊNCIA, etc.
     * </p>
     */
    @Column(name = "type", nullable = false)
    private TransactionType transactionType;
}