package com.springboot.microservice.customer.dao;

import com.springboot.microservice.customer.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAO extends CrudRepository<User, Integer> {

    Optional<User> findByUserName(String username);

    Boolean existsByUserName(String username);
}
