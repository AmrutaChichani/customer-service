package com.springboot.microservice.customer.dto;

import com.springboot.microservice.customer.entity.CustomerAddress;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class CustomerAddressResponseDTO {

    private Integer id;
    private String locality;
    private String city;
    private String state;
    private String country;

    @Pattern(regexp = "^[0-9]{6}$",message = "Enter 6 digit Pin code")
    private String pinCode;


    public static CustomerAddressResponseDTO from(CustomerAddress address){
        CustomerAddressResponseDTO dto=new CustomerAddressResponseDTO();
        dto.setId(address.getId());
        dto.setPinCode(address.getPinCode());
        dto.setCountry(address.getCountry());
        dto.setState(address.getState());
        dto.setCity(address.getCity());
        dto.setLocality(address.getLocality());
        return dto;
    }
}
