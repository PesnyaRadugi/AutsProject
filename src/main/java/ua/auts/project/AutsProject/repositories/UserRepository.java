package ua.auts.project.AutsProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import ua.auts.project.AutsProject.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByLogin(String login);
}
