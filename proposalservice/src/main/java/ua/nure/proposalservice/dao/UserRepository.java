package ua.nure.proposalservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.proposalservice.model.User;

public interface UserRepository extends JpaRepository<User, String> {
    User findByLogin(String login);
}
