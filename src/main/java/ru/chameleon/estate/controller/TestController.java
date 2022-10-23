package ru.chameleon.estate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chameleon.estate.converter.EstateDTOConverter;
import ru.chameleon.estate.dto.UserDTO;
import ru.chameleon.estate.entity.User;
import ru.chameleon.estate.exception.UserAlreadyExistException;
import ru.chameleon.estate.service.abstraction.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = {"http://localhost:3000/", "https://real-estate-value-calculator.herokuapp.com/"})
public class TestController {

    private final UserService userService;

    private final EstateDTOConverter converter;

    public TestController(UserService userService, EstateDTOConverter converter) {
        this.userService = userService;
        this.converter = converter;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> showAllUsersEndpoint() {
        List<User> usersList = userService.getUsers();
        if (usersList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(usersList);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> showUserEndpoint(@PathVariable("id") long userId) {
        if (userService.getUserById(userId) == null) {
            return ResponseEntity.notFound().build();
        }
        User showUser = userService.getUserById(userId);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(showUser);
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUserEndpoint(@RequestBody UserDTO userDto) throws UserAlreadyExistException {
        User user = converter.convertToEntity(userDto);
        userService.addUser(user);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUserEndpoint(@RequestBody UserDTO userDto) throws UserAlreadyExistException {
        User user = converter.convertToEntity(userDto);
        if (userService.getUserById(user.getUserId()) == null) {
            return ResponseEntity.notFound().build();
        }
        userService.updateUser(user, user.getUserId());
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUserEndpoint(@PathVariable("id") long id) {
        if (userService.getUserById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
