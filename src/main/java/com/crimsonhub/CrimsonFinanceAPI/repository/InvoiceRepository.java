package com.crimsonhub.CrimsonFinanceAPI.repository;

import com.crimsonhub.CrimsonFinanceAPI.domain.dto.card.InvoiceResponseDTO;
import com.crimsonhub.CrimsonFinanceAPI.domain.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query("SELECT new com.crimsonhub.CrimsonFinanceAPI.domain.dto.card.InvoiceResponseDTO(i.id, i.amountDue, i.dateDue, i.closingDate, i.paid) FROM Invoice i WHERE i.card.id = :cardId " +
            "AND MONTH(i.dateDue) = :month " +
            "AND YEAR(i.dateDue) = :year")
    List<InvoiceResponseDTO> findInvoicesInMonthByCardId(Long cardId, int month, int year);
}
