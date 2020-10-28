package com.librairie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.librairie.domain.security.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
