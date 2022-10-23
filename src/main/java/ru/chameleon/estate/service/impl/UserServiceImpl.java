package ru.chameleon.estate.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chameleon.estate.entity.User;
import ru.chameleon.estate.exception.UserAlreadyExistException;
import ru.chameleon.estate.repository.abstraction.RoleRepository;
import ru.chameleon.estate.repository.abstraction.UserRepository;
import ru.chameleon.estate.service.abstraction.UserService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
   // private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void addUser(User user) throws UserAlreadyExistException {
        if (userRepository.getUserByName(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistException("There is an account with that nickname: "
                    + user.getUsername());
        }

        if (userRepository.getUserByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + user.getEmail());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(User user, long id) throws UserAlreadyExistException {
        if (userRepository.getUserByName(user.getUsername()).isPresent()
                && !userRepository.getReferenceById(id).getUsername().equals(user.getUsername())) {
            throw new UserAlreadyExistException("There is an account with that nickname: " + user.getUsername());
        }

        if (userRepository.getUserByEmail(user.getEmail()).isPresent()
                && !userRepository.getReferenceById(id).getEmail().equals(user.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + user.getEmail());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        userRepository.deleteByUserId(id);
    }

    @Override
    public User getUserById(long id) {
        return userRepository.getReferenceById(id);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserByName(String name) {
        return userRepository.getUserByName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return userRepository.getUserByName(name).orElse(null);
    }
}
