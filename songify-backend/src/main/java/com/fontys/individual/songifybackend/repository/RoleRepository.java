package com.fontys.individual.songifybackend.repository;

import com.fontys.individual.songifybackend.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
