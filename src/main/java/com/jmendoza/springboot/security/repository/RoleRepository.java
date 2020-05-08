package com.jmendoza.springboot.security.repository;

import com.jmendoza.springboot.security.model.Role;
import com.jmendoza.springboot.security.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(Roles name);
}
