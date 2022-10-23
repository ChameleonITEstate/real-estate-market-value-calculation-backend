package ru.chameleon.estate.security.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.chameleon.estate.entity.Role;
import ru.chameleon.estate.entity.User;
import ru.chameleon.estate.security.init.abstraction.Init;
import ru.chameleon.estate.service.abstraction.RoleService;
import ru.chameleon.estate.service.abstraction.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class InitImpl implements Init {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public InitImpl(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @PostConstruct
    public void InitUserAndRole() {
        List<User> users = userService.getUsers();

        if (users.isEmpty()) {
            Role roleAdmin = new Role("ROLE_ADMIN");
            Role roleUser = new Role("ROLE_USER");
            Set<Role> adminRole = new HashSet<>();
            Set<Role> userRole = new HashSet<>();
            Set<Role> anyRole = new HashSet<>();
            adminRole.add(roleAdmin);
            userRole.add(roleUser);
            anyRole.add(roleAdmin);
            anyRole.add(roleUser);
            roleService.saveRole(roleAdmin);
            roleService.saveRole(roleUser);
            userService.addUser(new User(1L, "Maha", "Smirnova", "admin@mail.ru", "111", adminRole));
            userService.addUser(new User(2L, "Misha", "Krokodilov",  "user@mail.ru", "222", userRole));
            userService.addUser(new User(3L, "Dima", "Borisov",  "dimab@mail.ru", "333", userRole));
            userService.addUser(new User(4L,"Vasya", "Pupkin",  "vasyap@mail.ru", "444", userRole));
            userService.addUser(new User(5L,"Kostya", "Gradov",  "kostyag@mail.ru", "555", anyRole));
        }
    }
}
