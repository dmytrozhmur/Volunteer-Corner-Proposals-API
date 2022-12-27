package ua.nure.proposalservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.proposalservice.model.HelpProposal;
import ua.nure.proposalservice.model.Volunteer;

import java.util.List;

public interface OfferRepository extends JpaRepository<HelpProposal, String> {
    List<HelpProposal> findAllByOwner(Volunteer owner);
}
