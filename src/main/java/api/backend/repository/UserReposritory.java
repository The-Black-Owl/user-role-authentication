package api.backend.repository;

import api.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserReposritory extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email); //the email is the username
}
