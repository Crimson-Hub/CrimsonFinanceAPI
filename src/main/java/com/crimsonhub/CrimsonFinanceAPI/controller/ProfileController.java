package com.crimsonhub.CrimsonFinanceAPI.controller;

import com.crimsonhub.CrimsonFinanceAPI.domain.dto.profile.ChangePasswordDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.profile.ProfileResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.profile.ProfileSummaryResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.profile.ProfileUpdateDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.security.LoginDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.security.RegisterDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.security.TokenResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/register")
    public ResponseEntity<?> registerProfile(@Valid @RequestBody RegisterDTO data) {
        profileService.registerProfile(data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> loginProfile(@Valid @RequestBody LoginDTO data) {
        TokenResponseDTO token = profileService.loginProfile(data);
        return ResponseEntity.ok(token);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<?> updatePassword(@Valid @RequestBody ChangePasswordDTO data) {
        profileService.changePassword(data);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProfile(@Valid @RequestBody ProfileUpdateDTO data) {
        profileService.updateProfile(data);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProfileSummaryResponseDTO>> profiles() {
        List<ProfileSummaryResponseDTO> response = profileService.profiles();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<ProfileResponseDTO> profile(@PathVariable Long id) {
        ProfileResponseDTO response = profileService.profile(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/profile/summary/{id}")
    public ResponseEntity<ProfileSummaryResponseDTO> profileSummary(@PathVariable Long id) {
        ProfileSummaryResponseDTO response = profileService.profileSummary(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
}
