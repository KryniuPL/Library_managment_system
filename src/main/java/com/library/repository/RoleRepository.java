package com.library.repository;

import com.library.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Tworzy repozytorium danych z klasy Role
 */
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByName(String name);

}
