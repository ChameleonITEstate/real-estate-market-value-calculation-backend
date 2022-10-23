package ru.chameleon.estate.repository.abstraction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chameleon.estate.entity.Role;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
//
//    Role findByRoleName(String roleName);
//
//    Set<Role> findAllByUsersUserId(long id);
}
