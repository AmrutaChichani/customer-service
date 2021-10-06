package com.springboot.microservice.customer.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class CustomerAddressDTO {
    private Integer id;
    private String locality;
    private String city;
    private String state;
    private String country;
    private Integer customerId;

    @Pattern(regexp = "^[0-9]{6}$",message = "Enter 6 digit Pin code")
    private String pinCode;

}
