package com.crimsonhub.CrimsonFinanceAPI.service;

import com.crimsonhub.CrimsonFinanceAPI.domain.dto.card.CardAssignDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.card.CardResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.card.CardUpdateDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.dto.card.InvoiceAssignDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.entity.Card;
import com.crimsonhub.CrimsonFinanceAPI.domain.entity.Invoice;
import com.crimsonhub.CrimsonFinanceAPI.domain.entity.Profile;
import com.crimsonhub.CrimsonFinanceAPI.domain.entity.Transaction;
import com.crimsonhub.CrimsonFinanceAPI.exception.CardNotFoundException;
import com.crimsonhub.CrimsonFinanceAPI.exception.ProfileNotFoundException;
import com.crimsonhub.CrimsonFinanceAPI.repository.CardRepository;
import com.crimsonhub.CrimsonFinanceAPI.repository.ProfileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Serviço responsável pela gestão de cartões no sistema.
 * <p>
 *     Esta classe contém a lógica para atribuição, atualização, exclusão e recuperação de cartões e transações associadas a um perfil.
 * </p>
 *
 * @author Crimson Finance
 * @version 1.0
 * @since 2025-01-01
 */
@Service
public class CardService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Atribui um cartão a um perfil existente.
     *
     * @param profileId O ID do perfil ao qual o cartão será atribuído.
     * @param data Objeto {@link CardAssignDTO} contendo os dados do cartão a ser atribuído.
     * @throws ProfileNotFoundException Se o perfil com o ID fornecido não for encontrado.
     */
    public void assignCard(Long profileId, CardAssignDTO data) {
        Profile profileEntity = profileRepository.findById(profileId)
                .orElseThrow(() -> new ProfileNotFoundException(profileId));

        Card cardEntity = modelMapper.map(data, Card.class);
        cardEntity.setProfile(profileEntity);

        cardRepository.save(cardEntity);
    }

    /**
     * Atualiza os dados de um cartão existente.
     *
     * @param id O ID do cartão que será atualizado.
     * @param data Objeto {@link CardUpdateDTO} contendo os novos dados do cartão.
     * @throws CardNotFoundException Se o cartão com o ID fornecido não for encontrado.
     */
    public void updateCard(Long id, CardUpdateDTO data) {
        cardRepository.findById(id)
                .map(cardEntity -> {

                    modelMapper.map(data, cardEntity);

                    return cardRepository.save(cardEntity);
                })
                .orElseThrow(() -> new CardNotFoundException(id));
    }

    /**
     * Retorna um conjunto de cartões associados a um perfil.
     *
     * @param profileId O ID do perfil cujos cartões serão retornados.
     * @return Um conjunto de objetos {@link CardResponseDTO}.
     */
    public Set<CardResponseDTO> cards(Long profileId) {
        return cardRepository.findCardsByProfileId(profileId);
    }

    /**
     * Retorna as transações associadas a um cartão.
     *
     * @param id O ID do cartão cujas transações serão retornadas.
     * @return Um conjunto de objetos {@link Transaction}.
     */
    public Set<Transaction> transactions(Long id) {
        return cardRepository.findTransactionsByCardId(id);
    }

    public String cardsBalance(Long profileId) {
        return cardRepository.getCardsBalance(profileId);
    }

    /**
     * Atribui uma fatura a um cartão.
     *
     * @param id O ID do cartão ao qual a fatura será atribuída.
     * @param data Objeto {@link InvoiceAssignDTO} contendo os dados da fatura a ser atribuída.
     * @throws CardNotFoundException Se o cartão com o ID fornecido não for encontrado.
     */
    public void assignInvoice(Long id, InvoiceAssignDTO data) {
        cardRepository.findById(id)
                .map(cardEntity -> {

                    Invoice invoice = modelMapper.map(data, Invoice.class);
                    cardEntity.getInvoices().add(invoice);

                    return cardRepository.save(cardEntity);
                })
                .orElseThrow(() -> new CardNotFoundException(id));
    }

    /**
     * Retorna as faturas associadas a um cartão em um mês específico.
     *
     * @param id   O ID do cartão cujas faturas serão retornadas.
     * @param month O mês das faturas a serem retornadas.
     * @param year O ano das faturas a serem retornadas.
     * @return Um conjunto de objetos {@link Invoice}.
     */
    public Set<Invoice> invoices(Long id, int month, int year) {
        return cardRepository.findInvoicesInMonthByCardId(id, month, year);
    }

    /**
     * Exclui um cartão pelo ID.
     *
     * @param id O ID do cartão que será excluído.
     */
    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }
}
