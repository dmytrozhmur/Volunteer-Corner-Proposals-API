package ua.nure.proposalservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.proposalservice.model.Volunteer;

public interface VolunteerRepository extends JpaRepository<Volunteer, String> {
    /**
     * TODO if wrong, check for userLogin
     */
    Volunteer findByUserLogin(String login);
}
