package ua.nure.proposalservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.proposalservice.model.HelpProposal;

public interface OfferRepository extends JpaRepository<HelpProposal, String> {
}
