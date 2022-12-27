package ua.nure.proposalservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.proposalservice.model.HelpProposalPhoto;

public interface PhotoRepository extends JpaRepository<HelpProposalPhoto, String> {
}
