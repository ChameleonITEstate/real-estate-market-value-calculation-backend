package ru.chameleon.estate.service.impl;

import org.springframework.stereotype.Service;
import ru.chameleon.estate.entity.Role;
import ru.chameleon.estate.repository.abstraction.RoleRepository;
import ru.chameleon.estate.service.abstraction.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
