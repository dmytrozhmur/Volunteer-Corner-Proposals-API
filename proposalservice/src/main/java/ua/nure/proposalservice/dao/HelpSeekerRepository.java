package ua.nure.proposalservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.proposalservice.model.HelpSeeker;
import ua.nure.proposalservice.model.Volunteer;

public interface HelpSeekerRepository extends JpaRepository<HelpSeeker, String> {
}
