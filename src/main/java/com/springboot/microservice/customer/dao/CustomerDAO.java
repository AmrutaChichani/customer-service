package com.springboot.microservice.customer.dao;


import com.springboot.microservice.customer.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerDAO extends CrudRepository<Customer,Integer> {

}
