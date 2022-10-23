package ru.chameleon.estate.service.abstraction;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.chameleon.estate.entity.User;
import ru.chameleon.estate.exception.UserAlreadyExistException;

import java.util.List;
import java.util.Optional;


public interface UserService extends UserDetailsService {
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    void addUser(User user) throws UserAlreadyExistException;

    User updateUser(User user, long id) throws UserAlreadyExistException;

    void deleteUser(long id);

    User getUserById(long id);

    List<User> getUsers();

    Optional<User> getUserByName(String name);
}
