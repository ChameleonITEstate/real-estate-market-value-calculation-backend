package ru.chameleon.estate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chameleon.estate.dto.UserDTO;
import ru.chameleon.estate.entity.User;
import ru.chameleon.estate.exception.UserAlreadyExistException;
import ru.chameleon.estate.service.abstraction.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = {"http://localhost:3000/", "https://real-estate-value-calculator.herokuapp.com/"})
public class RegistrationController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> usersList = userService.getUsers();
        if (usersList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(usersList);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> showUserEndpoint(@PathVariable("id") long userId) {
        if (userService.getUserById(userId) == null){
            return ResponseEntity.notFound().build();
        }
        User showUser = userService.getUserById(userId);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(showUser);
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user) throws UserAlreadyExistException {
        userService.addUser(user);
        return ResponseEntity.ok().body(user);
    }

//    @PutMapping("/users")
//    public ResponseEntity<User> updateUser(@RequestBody UserDTO userDto) throws UserAlreadyExistException {
//        if (userService.getUserById(userDto.userId()) == null){
//            return ResponseEntity.notFound().build();
//        }
//        userService.updateUser(userDto, userDto.userId());
//        return ResponseEntity.ok().body(userDto);
//    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
        if (userService.getUserById(id) == null){
            return ResponseEntity.notFound().build();
        }
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
