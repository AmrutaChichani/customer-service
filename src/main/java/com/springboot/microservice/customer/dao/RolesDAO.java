package com.springboot.microservice.customer.dao;

import com.springboot.microservice.customer.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesDAO extends CrudRepository<Role,Integer> {
    Optional<Role> findByRoles(String role);
}

