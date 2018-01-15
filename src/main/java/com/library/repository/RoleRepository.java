package com.library.repository;

import com.library.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer>
{
    Role findByRole(String role);
}
