package ua.nure.proposalservice.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.proposalservice.model.UserHelpProposal;

public interface HelpProposalRepository extends JpaRepository<UserHelpProposal, String> {
}
