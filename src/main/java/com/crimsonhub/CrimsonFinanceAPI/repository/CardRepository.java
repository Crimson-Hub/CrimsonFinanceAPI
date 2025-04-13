package com.crimsonhub.CrimsonFinanceAPI.repository;

import com.crimsonhub.CrimsonFinanceAPI.domain.dto.card.CardListResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.card.CardsDashboardResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    @Query(value = "SELECT " +
            "c.id, " +
            "c.credit_limit AS creditLimit, " +
            "c.current_expenses AS currentExpenses, " +
            "cf.name AS cardFlag, " +
            "c.description " +
            "FROM card c " +
            "JOIN card_flag cf ON c.card_flag_id = cf.id " +
            "WHERE c.profile_id = :profileId", nativeQuery = true)
    List<CardListResponseDTO> findCardsByProfileId(Long profileId);

    @Query("SELECT c.currentExpenses FROM Card c WHERE c.profile.id = :profileId")
    BigDecimal getTotalCardsBalance(Long profileId);

    @Query(value = "SELECT " +
            "c.id," +
            "cf.name AS cardFlag, " +
            "c.description " +
            "FROM card c " +
            "JOIN card_flag cf ON c.card_flag_id = cf.id " +
            "WHERE c.profile_id = :profileId " +
            "ORDER BY c.current_expenses DESC LIMIT 3", nativeQuery = true)
    List<CardsDashboardResponseDTO> findTopCardsByProfileId(Long profileId);
}
