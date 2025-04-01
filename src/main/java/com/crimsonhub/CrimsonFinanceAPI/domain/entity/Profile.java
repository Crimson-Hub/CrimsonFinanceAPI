package com.crimsonhub.CrimsonFinanceAPI.domain.entity;

import com.crimsonhub.CrimsonFinanceAPI.domain.type.RoleType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Entidade que representa o perfil de um usuário no sistema.
 * <p>
 * Esta classe é mapeada para a tabela <code>profile</code> no banco de dados e implementa a interface {@link UserDetails}
 * do Spring Security, permitindo a autenticação e autorização do usuário.
 * </p>
 *
 * <p><b>Campos principais:</b></p>
 * <ul>
 *     <li><b>id</b>: Identificador único do perfil.</li>
 *     <li><b>profileDetails</b>: Detalhes pessoais do usuário (relacionamento um-para-um).</li>
 *     <li><b>email</b>: Endereço de e-mail único e obrigatório do usuário.</li>
 *     <li><b>password</b>: Senha criptografada do usuário.</li>
 *     <li><b>roleType</b>: Tipo de papel (role) do usuário, definido pela enumeração {@link RoleType}.</li>
 *     <li><b>createdAt</b>: Data e hora de criação do perfil.</li>
 * </ul>
 *
 * <p><b>Implementação de Spring Security:</b></p>
 * <ul>
 *     <li>{@link #getAuthorities()}: Retorna as permissões do usuário com base no papel definido.</li>
 *     <li>{@link #getUsername()}: Retorna o e-mail como o nome de usuário.</li>
 * </ul>
 *
 * @author Crimson Finance
 * @version 1.0
 * @since 2024-01-01
 */
@Entity
@Table(name = "profile")
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
public class Profile implements UserDetails {

    /**
     * Identificador único do perfil.
     * Gerado automaticamente pela estratégia de incremento definida.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Endereço de e-mail do usuário.
     * Este campo é único e obrigatório, com um limite de 100 caracteres.
     */
    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;

    /**
     * Senha criptografada do usuário.
     * Este campo é obrigatório, com um limite de 12 caracteres.
     */
    @Column(name = "password", nullable = false, length = 12)
    private String password;

    /**
     * Papel (role) do usuário no sistema.
     * Este campo é obrigatório e utiliza a enumeração {@link RoleType}.
     */
    @Column(name = "role", nullable = false)
    private RoleType roleType = RoleType.USER;

    /**
     * Nome completo do usuário.
     * Este campo é obrigatório e possui um limite de 60 caracteres.
     */
    @Column(name = "full_name", nullable = false, length = 60)
    private String fullName;

    /**
     * Nome preferencial do usuário.
     * Campo opcional com um limite de 60 caracteres.
     */
    @Column(name = "preferred_name", length = 60)
    private String preferredName;

    /**
     * Data de nascimento do usuário.
     * Representada no formato de {@link Date}.
     */
    @Column(name = "birthday")
    private Date birthday;

    /**
     * Número de telefone do usuário.
     * Campo opcional com um limite de 13 caracteres.
     */
    @Column(name = "phone", length = 13)
    private String phone;

    /**
     * Nacionalidade do usuário.
     * Campo opcional com um limite de 12 caracteres.
     */
    @Column(name = "nationality", length = 12)
    private String nationality;

    /**
     * Número de identificação único do usuário.
     * Este campo é obrigatório e deve ser único, com um limite de 14 caracteres.
     */
    @Column(name = "identification_number", unique = true, nullable = false, length = 14)
    private String identificationNumber;

    /**
     * CEP associado ao endereço do usuário.
     * Campo opcional com um limite de 8 caracteres.
     */
    @Column(name = "cep", length = 8)
    private String cep;

    /**
     * Data e hora de criação do perfil.
     * Este campo é obrigatório e utiliza o tipo {@link Timestamp}.
     */
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdAt;

    /**
     * Retorna as permissões do usuário com base no papel definido.
     * <p>
     * Permissões são derivadas de {@link RoleType}:
     * <ul>
     *     <li>{@link RoleType#ADMIN}: Acesso total (ADMIN, MOD, USER).</li>
     *     <li>{@link RoleType#MOD}: Permissões de moderador (MOD, USER).</li>
     *     <li>{@link RoleType#USER}: Permissões básicas (USER).</li>
     * </ul>
     * </p>
     *
     * @return Coleção de permissões como instâncias de {@link GrantedAuthority}.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.roleType == RoleType.ADMIN)
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_MOD"),
                    new SimpleGrantedAuthority("ROLE_USER"));
        else if (this.roleType == RoleType.MOD)
            return List.of(
                    new SimpleGrantedAuthority("ROLE_MOD"),
                    new SimpleGrantedAuthority("ROLE_USER"));
        else
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    /**
     * Retorna o endereço de e-mail como o nome de usuário para autenticação.
     *
     * @return E-mail do usuário.
     */
    @Override
    public String getUsername() {
        return email;
    }
}
