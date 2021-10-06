package com.springboot.microservice.customer.dao;

import com.springboot.microservice.customer.entity.CustomerAddress;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerAddressDAO extends CrudRepository<CustomerAddress,Integer> {

    public List<CustomerAddress> findByCustomerId(Integer customerId);
}
