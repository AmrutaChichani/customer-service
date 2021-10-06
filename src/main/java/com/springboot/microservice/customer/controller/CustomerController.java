package com.springboot.microservice.customer.controller;

import com.springboot.microservice.customer.dto.CustomerDTO;
import com.springboot.microservice.customer.dto.CustomerResponseDTO;
import com.springboot.microservice.customer.entity.Customer;
import com.springboot.microservice.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerServices;

    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> getCustomers() {
        List<Customer> customerList=customerServices.findAll();
        List<CustomerResponseDTO> customerDtoList=new ArrayList<>();
        for (Customer customer:customerList){
            customerDtoList.add(CustomerResponseDTO.from(customer));
        }
        return ResponseEntity.ok().body(customerDtoList);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable("customerId") Integer customerId) {
        CustomerResponseDTO result=CustomerResponseDTO.from(customerServices.findByCustomerId(customerId));
        return ResponseEntity.ok().body(result);
    }

    @PostMapping
    public ResponseEntity<Integer> addOrUpdateCustomer(@Valid @RequestBody CustomerDTO customer) {

        Integer result=customerServices.addCustomer(customer);
        return ResponseEntity.ok().body(result);
    }


    @DeleteMapping("/{customerId}")
    public ResponseEntity<Integer> deleteCustomerById(@PathVariable("customerId") Integer customerId) {
        customerServices.deleteCustomerById(customerId);
        return ResponseEntity.ok().body(customerId);
    }
}
