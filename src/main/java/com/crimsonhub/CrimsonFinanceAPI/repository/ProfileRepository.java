package com.crimsonhub.CrimsonFinanceAPI.repository;

import com.crimsonhub.CrimsonFinanceAPI.domain.dto.profile.ProfileResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.profile.ProfileSummaryResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query("SELECT p FROM Profile p WHERE p.email = :email")
    Optional<Profile> findByEmail(String email);

    @Query("SELECT new com.crimsonhub.CrimsonFinanceAPI.domain.dto.profile.ProfileSummaryResponseDTO" +
            "(p.id, p.email) FROM Profile p WHERE p.id = :id")
    Optional<ProfileSummaryResponseDTO> findProfileSummaryById(Long id);

    @Query("SELECT new com.crimsonhub.CrimsonFinanceAPI.domain.dto.profile.ProfileResponseDTO(" +
            "p.email, p.fullName, p.preferredName, p.birthday, p.phone, " +
            "p.nationality, p.identificationNumber, p.cep) " +
            "FROM Profile p WHERE p.id = :id")
    Optional<ProfileResponseDTO> findProfileById(Long id);

    @Query("SELECT new com.crimsonhub.CrimsonFinanceAPI.domain.dto.profile.ProfileSummaryResponseDTO" +
            "(p.id, p.email) FROM Profile p")
    List<ProfileSummaryResponseDTO> findAllProfiles();
}