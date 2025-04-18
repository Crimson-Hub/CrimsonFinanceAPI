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

import java.util.List;
import java.util.Optional;

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

    public void registerProfile(RegisterDTO data) {
        Optional<Profile> profileEntity = profileRepository.findByEmail(data.email());

        if (profileEntity.isPresent())
            throw new ProfileExistsException(data.email());

        Profile profile = modelMapper.map(data, Profile.class);
        profile.setPassword(passwordEncoder.encode(data.password()));

        profileRepository.save(profile);
    }

    public TokenResponseDTO loginProfile(LoginDTO data) {
        Profile profileEntity = profileRepository.findByEmail(data.email())
                .orElseThrow(() -> new ProfileNotFoundException(data.email()));

        if (passwordDoesNotMatch(data.password(), profileEntity.getPassword()))
            throw new InvalidPasswordException(profileEntity.getId());

        String token = tokenService.generateToken(profileEntity);

        return new TokenResponseDTO(token);
    }

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

    public List<ProfileSummaryResponseDTO> profiles() {
        return profileRepository.findAllProfiles();
    }

    public ProfileSummaryResponseDTO profileSummary(Long id) {
        return profileRepository.findProfileSummaryById(id)
                .orElseThrow(() -> new ProfileNotFoundException(id));
    }

    public ProfileResponseDTO profile(Long id) {
        return profileRepository.findProfileById(id)
                .orElseThrow(() -> new ProfileNotFoundException(id));
    }

    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws ProfileNotFoundException {
        return profileRepository.findByEmail(email)
                .orElseThrow(() -> new ProfileNotFoundException(email));
    }

    private boolean passwordDoesNotMatch(String password, String databasePassword) {
        return !passwordEncoder.matches(password, databasePassword);
    }
}
