package com.crimsonhub.CrimsonFinanceAPI.repository;

import com.crimsonhub.CrimsonFinanceAPI.domain.dto.profile.ProfileResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.profile.ProfileSummaryResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

/**
 * Repositório para a entidade {@link Profile}.
 * <p>
 * Esta interface gerencia as operações de persistência para a entidade {@link Profile}.
 * Ela herda de {@link JpaRepository}, fornecendo métodos padrão para CRUD, além de consultas personalizadas
 * com o uso da anotação {@link Query}.
 * </p>
 *
 * <p><b>Consultas Personalizadas:</b></p>
 * <ul>
 *     <li><b>findByEmail</b>: Retorna um perfil baseado no e-mail fornecido.</li>
 *     <li><b>findProfileSummaryById</b>: Retorna um resumo do perfil (id, e-mail, data de criação) baseado no ID do perfil.</li>
 *     <li><b>findProfileInfoById</b>: Retorna informações detalhadas sobre um perfil, incluindo os dados de perfil adicionais (nome completo, nome preferido, etc.) baseado no ID do perfil.</li>
 * </ul>
 *
 * <p><b>Annotations:</b></p>
 * <ul>
 *     <li>{@link Repository}: Define a classe como um repositório.</li>
 * </ul>
 *
 * @author Crimson Solutions
 * @version 1.0
 * @since 2024-01-01
 */
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    /**
     * Retorna um {@link Profile} com base no e-mail fornecido.
     *
     * @param email E-mail do perfil a ser recuperado.
     * @return Um {@link Optional} contendo o perfil encontrado ou vazio se o perfil não existir.
     */
    @Query("SELECT p FROM Profile p WHERE p.email = :email")
    Optional<Profile> findByEmail(String email);

    /**
     * Retorna um resumo do perfil (id, e-mail, data de criação) baseado no ID do perfil.
     *
     * @param id ID do perfil.
     * @return Um {@link Optional} contendo o resumo do perfil encontrado ou vazio se o perfil não existir.
     */
    @Query("SELECT new com.crimsonhub.CrimsonFinanceAPI.domain.dto.profile.ProfileSummaryResponseDTO" +
            "(p.id, p.email, p.createdAt) FROM Profile p WHERE p.id = :id")
    Optional<ProfileSummaryResponseDTO> findProfileSummaryById(Long id);

    /**
     * Retorna informações detalhadas sobre um perfil, incluindo os dados de perfil adicionais,
     * como nome completo, nome preferido, aniversário, telefone, nacionalidade, número de identificação e CEP.
     *
     * @param id ID do perfil.
     * @return Um {@link Optional} contendo as informações detalhadas do perfil encontrado ou vazio se o perfil não existir.
     */
    @Query("SELECT new com.crimsonhub.CrimsonFinanceAPI.domain.dto.profile.ProfileResponseDTO(" +
            "p.email, p.fullName, p.preferredName, p.birthday, p.phone, " +
            "p.nationality, p.identificationNumber, p.cep) " +
            "FROM Profile p WHERE p.id = :id")
    Optional<ProfileResponseDTO> findProfileById(Long id);

    @Query("SELECT new com.crimsonhub.CrimsonFinanceAPI.domain.dto.profile.ProfileSummaryResponseDTO" +
            "(p.id, p.email, p.createdAt) FROM Profile p")
    Set<ProfileSummaryResponseDTO> findAllProfiles();
}
