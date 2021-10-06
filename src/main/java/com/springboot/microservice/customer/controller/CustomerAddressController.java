package com.springboot.microservice.customer.controller;

import com.springboot.microservice.customer.dto.CustomerAddressDTO;
import com.springboot.microservice.customer.service.CustomerAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/customer/address")
public class CustomerAddressController {
    @Autowired
    private CustomerAddressService customerAddressServices;

    @PostMapping
    public ResponseEntity<Integer> addOrUpdateAddress(@Valid @RequestBody CustomerAddressDTO address) {

        Integer result= customerAddressServices.addOrUpdateAddress(address);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Integer> deleteAddressById(@PathVariable("addressId") Integer addressId) {
        customerAddressServices.deleteAddress(addressId);
        return ResponseEntity.ok().body(addressId);
    }

}