package de.cook2gether.backend.repositories;

import de.cook2gether.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmailAddress(String emailAddress);
}
