package com.crimsonhub.CrimsonFinanceAPI.controller;

import jakarta.validation.Valid;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.profile.ChangePasswordDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.profile.ProfileResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.profile.ProfileSummaryResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.profile.ProfileUpdateDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.security.LoginDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.security.RegisterDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.security.TokenResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Controlador responsável por gerenciar operações relacionadas a perfis de usuário.
 * <p>
 * Este controlador expõe endpoints REST para registro, autenticação, atualização,
 * recuperação de informações de perfis e gerenciamento de senhas.
 * </p>
 *
 * @author Crimson Finance
 * @version 1.0
 * @since 2025-01-01
 */
@RestController
@RequestMapping("api/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    /**
     * Registra um novo perfil de usuário.
     *
     * @param data Objeto {@link RegisterDTO} contendo os detalhes para registro.
     * @return Resposta com status HTTP 201 (Created) em caso de sucesso.
     */
    @PostMapping(
            value = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            headers = {"Content-Type=application/json", "Accept=application/json"}
    )
    public ResponseEntity<?> registerProfile(@Valid @RequestBody RegisterDTO data) {
        profileService.registerProfile(data);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Realiza a autenticação de um perfil.
     *
     * @param data Objeto {@link LoginDTO} contendo as credenciais de login.
     * @return Resposta contendo um {@link TokenResponseDTO} com o token de autenticação.
     */
    @PostMapping(
            value = "/login",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            headers = {"Content-Type=application/json", "Accept=application/json"}
    )
    public ResponseEntity<TokenResponseDTO> loginProfile(@Valid @RequestBody LoginDTO data) {
        TokenResponseDTO token = profileService.loginProfile(data);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    /**
     * Altera a senha de um perfil de usuário.
     *
     * @param data Objeto {@link ChangePasswordDTO} contendo as informações de troca de senha.
     * @return Resposta com status HTTP 200 (OK) em caso de sucesso.
     */
    @PostMapping(
            value = "/changePassword",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            headers = {"Content-Type=application/json", "Accept=application/json"}
    )
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordDTO data) {
        profileService.changePassword(data);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Atualiza os detalhes de um perfil de usuário.
     *
     * @param data Objeto {@link ProfileUpdateDTO} contendo as informações atualizadas do perfil.
     * @return Resposta com status HTTP 200 (OK) em caso de sucesso.
     */
    @PostMapping(
            value = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            headers = {"Content-Type=application/json", "Accept=application/json"}
    )
    public ResponseEntity<?> updateProfile(@Valid @RequestBody ProfileUpdateDTO data) {
        profileService.updateProfile(data);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Retorna uma lista de perfis resumidos.
     *
     * @return Resposta contendo um conjunto de {@link ProfileSummaryResponseDTO}.
     */
    @GetMapping(
            value = "/profiles",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Set<ProfileSummaryResponseDTO>> profiles() {
        Set<ProfileSummaryResponseDTO> response = profileService.profiles();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Retorna um resumo de um perfil específico.
     *
     * @param id ID do perfil a ser consultado.
     * @return Resposta contendo um {@link ProfileSummaryResponseDTO}.
     */
    @GetMapping(
            value = "/profileSummary",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ProfileSummaryResponseDTO> profileSummary(@RequestParam Long id) {
        ProfileSummaryResponseDTO response = profileService.profileSummary(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Retorna os detalhes completos de um perfil.
     *
     * @param id ID do perfil a ser consultado.
     * @return Resposta contendo um {@link ProfileResponseDTO}.
     */
    @GetMapping(
            value = "/profile",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ProfileResponseDTO> profile(@RequestParam Long id) {
        ProfileResponseDTO response = profileService.profile(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Exclui um perfil de usuário.
     *
     * @param id ID do perfil a ser excluído.
     * @return Resposta com status HTTP 200 (OK) em caso de sucesso.
     */
    @DeleteMapping(
            value = "/delete"
    )
    public ResponseEntity<?> deleteProfile(@RequestParam Long id) {
        profileService.deleteProfile(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
