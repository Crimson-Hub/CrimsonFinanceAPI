package com.crimsonhub.CrimsonFinanceAPI.service;

import com.crimsonhub.CrimsonFinanceAPI.domain.dto.card.*;
import com.crimsonhub.CrimsonFinanceAPI.domain.entity.Card;
import com.crimsonhub.CrimsonFinanceAPI.domain.entity.Invoice;
import com.crimsonhub.CrimsonFinanceAPI.exception.CardNotFoundException;
import com.crimsonhub.CrimsonFinanceAPI.exception.ProfileNotFoundException;
import com.crimsonhub.CrimsonFinanceAPI.repository.CardRepository;
import com.crimsonhub.CrimsonFinanceAPI.repository.InvoiceRepository;
import com.crimsonhub.CrimsonFinanceAPI.repository.ProfileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CardService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ModelMapper modelMapper;

    public void assignCard(Long profileId, CardAssignDTO data) {
        profileRepository.findById(profileId).map(profileEntity -> {

            Card card = modelMapper.map(data, Card.class);
            card.setProfile(profileEntity);

            return cardRepository.save(card);
        })
                .orElseThrow(() -> new ProfileNotFoundException(profileId));
    }

    public void updateCard(Long id, CardUpdateDTO data) {
        cardRepository.findById(id).map(cardEntity -> {

            modelMapper.map(data, cardEntity);

            return cardRepository.save(cardEntity);
        })
                .orElseThrow(() -> new CardNotFoundException(id));
    }

    public List<CardListResponseDTO> findCardsByProfileId(Long profileId) {
        return cardRepository.findCardsByProfileId(profileId);
    }

    public BigDecimal getTotalCardsBalance(Long profileId) {
        return cardRepository.getTotalCardsBalance(profileId);
    }

    public List<CardsDashboardResponseDTO> findTopCardsByProfileId(Long profileId) {
        return cardRepository.findTopCardsByProfileId(profileId);
    }

    public void assignInvoice(Long id, InvoiceAssignDTO data) {
        cardRepository.findById(id).map(cardEntity -> {

            Invoice invoice = modelMapper.map(data, Invoice.class);
            invoice.setCard(cardEntity);

            return invoiceRepository.save(invoice);
        })
                .orElseThrow(() -> new CardNotFoundException(id));
    }

    public List<InvoiceResponseDTO> findInvoicesInMonthByCardId(Long cardId, int month, int year) {
        return invoiceRepository.findInvoicesInMonthByCardId(cardId, month, year);
    }

    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }
}
