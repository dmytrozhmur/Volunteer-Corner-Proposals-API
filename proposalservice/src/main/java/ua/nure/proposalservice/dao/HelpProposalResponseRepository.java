package ua.nure.proposalservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.proposalservice.model.HelpProposalResponse;

public interface HelpProposalResponseRepository extends JpaRepository<HelpProposalResponse, String> {
}
