package com.example.model.repositories;

import com.example.model.authorization.EnumRole;
import com.example.model.authorization.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName(EnumRole role);
}
