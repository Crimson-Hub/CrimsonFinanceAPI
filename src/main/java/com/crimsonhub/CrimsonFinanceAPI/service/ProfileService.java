package com.crimsonhub.CrimsonFinanceAPI.service;

import com.crimsonhub.CrimsonFinanceAPI.domain.dto.profile.ChangePasswordDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.profile.ProfileResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.profile.ProfileSummaryResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.profile.ProfileUpdateDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.security.LoginDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.security.RegisterDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.security.TokenResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.entity.Profile;
import com.crimsonhub.CrimsonFinanceAPI.exception.InvalidPasswordException;
import com.crimsonhub.CrimsonFinanceAPI.exception.ProfileExistsException;
import com.crimsonhub.CrimsonFinanceAPI.exception.ProfileNotFoundException;
import com.crimsonhub.CrimsonFinanceAPI.repository.ProfileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * Serviço responsável pela gestão dos perfis de usuários no sistema.
 * <p>
 * Esta classe contém a lógica para registrar, fazer login, alterar senha, atualizar perfil e excluir perfis de usuário.
 * Também realiza operações de recuperação de perfis e resumos de perfis.
 * </p>
 *
 * @author Crimson Finance
 * @version 1.0
 * @since 2025-01-01
 */
@Service
public class ProfileService implements UserDetailsService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Registra um novo perfil no sistema.
     *
     * @param data Objeto {@link RegisterDTO} contendo as informações do novo perfil a ser registrado.
     * @throws ProfileExistsException Se já existir um perfil com o email fornecido.
     */
    public void registerProfile(RegisterDTO data) {
        Optional<Profile> profileEntity = profileRepository.findByEmail(data.email());

        if (profileEntity.isPresent())
            throw new ProfileExistsException(data.email());

        Profile profile = modelMapper.map(data, Profile.class);
        profile.setPassword(passwordEncoder.encode(data.password()));

        profileRepository.save(profile);
    }

    /**
     * Realiza o login de um perfil no sistema.
     *
     * @param data Objeto {@link LoginDTO} contendo as credenciais de login (email e senha).
     * @return {@link TokenResponseDTO} com o token gerado para o usuário autenticado.
     * @throws ProfileNotFoundException Se o perfil com o email fornecido não for encontrado.
     * @throws InvalidPasswordException Se a senha fornecida não corresponder à senha armazenada.
     */
    public TokenResponseDTO loginProfile(LoginDTO data) {
        Profile profileEntity = profileRepository.findByEmail(data.email())
                .orElseThrow(() -> new ProfileNotFoundException(data.email()));

        if (passwordDoesNotMatch(data.password(), profileEntity.getPassword()))
            throw new InvalidPasswordException(profileEntity.getId());

        String token = tokenService.generateToken(profileEntity);

        return new TokenResponseDTO(token);
    }

    /**
     * Altera a senha de um perfil existente.
     *
     * @param data Objeto {@link ChangePasswordDTO} contendo o email, a senha atual e a nova senha.
     * @throws ProfileNotFoundException Se o perfil com o email fornecido não for encontrado.
     * @throws InvalidPasswordException Se a senha atual fornecida não corresponder à senha armazenada.
     */
    public void changePassword(ChangePasswordDTO data) {
        profileRepository.findByEmail(data.email())
                .map(profileEntity -> {
                    if (passwordDoesNotMatch(data.password(), profileEntity.getPassword()))
                        throw new InvalidPasswordException(profileEntity.getId());

                    profileEntity.setPassword(passwordEncoder.encode(data.newPassword()));

                    return profileRepository.save(profileEntity);
                })
                .orElseThrow(() -> new ProfileNotFoundException(data.email()));
    }

    /**
     * Atualiza as informações de um perfil existente.
     *
     * @param data Objeto {@link ProfileUpdateDTO} contendo o email e as informações atualizadas do perfil.
     * @throws ProfileNotFoundException Se o perfil com o email fornecido não for encontrado.
     * @throws InvalidPasswordException Se a senha fornecida não corresponder à senha armazenada.
     */
    public void updateProfile(ProfileUpdateDTO data) {
        profileRepository.findByEmail(data.email())
                .map(profileEntity -> {
                    if (passwordDoesNotMatch(data.password(), profileEntity.getPassword()))
                        throw new InvalidPasswordException(profileEntity.getId());

                    modelMapper.map(data.updatedProfile(), profileEntity);

                    return profileRepository.save(profileEntity);
                })
                .orElseThrow(() -> new ProfileNotFoundException(data.email()));
    }

    /**
     * Retorna todos os perfis cadastrados no sistema.
     *
     * @return Um conjunto de objetos {@link ProfileSummaryResponseDTO}.
     */
    public Set<ProfileSummaryResponseDTO> profiles() {
        return profileRepository.findAllProfiles();
    }

    /**
     * Retorna um resumo do perfil com o ID fornecido.
     *
     * @param id O ID do perfil que será recuperado.
     * @return Um objeto {@link ProfileSummaryResponseDTO} com o resumo do perfil.
     * @throws ProfileNotFoundException Se o perfil com o ID fornecido não for encontrado.
     */
    public ProfileSummaryResponseDTO profileSummary(Long id) {
        return profileRepository.findProfileSummaryById(id)
                .orElseThrow(() -> new ProfileNotFoundException(id));
    }

    /**
     * Retorna o perfil completo com o ID fornecido.
     *
     * @param id O ID do perfil que será recuperado.
     * @return Um objeto {@link ProfileResponseDTO} com as informações completas do perfil.
     * @throws ProfileNotFoundException Se o perfil com o ID fornecido não for encontrado.
     */
    public ProfileResponseDTO profile(Long id) {
        return profileRepository.findProfileById(id)
                .orElseThrow(() -> new ProfileNotFoundException(id));
    }

    /**
     * Exclui um perfil com o ID fornecido.
     *
     * @param id O ID do perfil que será excluído.
     */
    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }

    /**
     * Carrega os detalhes de um perfil com base no email fornecido.
     *
     * @param email O email do perfil a ser carregado.
     * @return Um objeto {@link UserDetails} representando o perfil.
     * @throws ProfileNotFoundException Se o perfil com o email fornecido não for encontrado.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws ProfileNotFoundException {
        return profileRepository.findByEmail(email)
                .orElseThrow(() -> new ProfileNotFoundException(email));
    }

    /**
     * Verifica se a senha fornecida não corresponde à senha armazenada.
     *
     * @param password A senha fornecida.
     * @param databasePassword A senha armazenada no banco de dados.
     * @return True se as senhas não corresponderem, false caso contrário.
     */
    private boolean passwordDoesNotMatch(String password, String databasePassword) {
        return !passwordEncoder.matches(password, databasePassword);
    }
}
