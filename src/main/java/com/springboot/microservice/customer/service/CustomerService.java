package com.springboot.microservice.customer.service;

import com.springboot.microservice.customer.dao.CustomerDAO;
import com.springboot.microservice.customer.dto.CustomerDTO;
import com.springboot.microservice.customer.entity.Customer;
import com.springboot.microservice.customer.exception.InvalidCustomerIdException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CustomerService {

    @Autowired
    private CustomerDAO customerDao;

    @Autowired
    private CustomerAddressService addressServices;

    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        customerDao.findAll().forEach(customers::add);
        return customers;
    }

    public Customer findByCustomerId(Integer customerId) {
        Optional<Customer> customerOptional = customerDao.findById(customerId);
        if (!customerOptional.isPresent()) {
            log.info("Invalid customer id:{}", customerId);
            throw new InvalidCustomerIdException();
        }
        return customerOptional.get();

    }

    public Integer addCustomer(CustomerDTO customer) {
        Customer theCustomer = new Customer();
        if (customer.getId() != null)
            theCustomer.setId(customer.getId());
        theCustomer.setName(customer.getName());
        theCustomer.setActiveFlag(true);
        theCustomer.setEmail(customer.getEmail());
        theCustomer.setContactNo(customer.getContactNo());
        customerDao.save(theCustomer);
        return theCustomer.getId();
    }

    public void deleteCustomerById(Integer customerId) {
        if (customerDao.existsById(customerId)) {
            customerDao.deleteById(customerId);
        } else {
            log.info("Invalid customer id :{}", customerId);
            throw new InvalidCustomerIdException();
        }
    }

}