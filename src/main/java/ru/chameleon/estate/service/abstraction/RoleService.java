package ru.chameleon.estate.service.abstraction;

import ru.chameleon.estate.entity.Role;

import java.util.List;

public interface RoleService {
    void saveRole(Role role);

    List<Role> getAllRoles();
}
