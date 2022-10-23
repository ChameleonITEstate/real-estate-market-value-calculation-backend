package ru.chameleon.estate.repository.abstraction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import ru.chameleon.estate.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    void deleteByUserId(long id);

    Optional<User> getUserByName(String name);

    Optional<User> getUserByEmail(String mail);
}
