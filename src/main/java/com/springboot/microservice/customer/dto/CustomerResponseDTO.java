package com.springboot.microservice.customer.dto;

import com.springboot.microservice.customer.entity.Customer;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CustomerResponseDTO {

    private Integer id;
    private String name;
    private String email;
    private String contactNo;
    private List<CustomerAddressResponseDTO> addresses;

    public static CustomerResponseDTO from(Customer customer){
        CustomerResponseDTO dto=new CustomerResponseDTO();
        if(customer.getId()!=null)
            dto.setId(customer.getId());

        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setContactNo(customer.getContactNo());
        if(customer.getCustomerAddresses()!=null) {
            List<CustomerAddressResponseDTO> addressDtoList = new ArrayList<>();
            customer.getCustomerAddresses().forEach(address ->
                    addressDtoList.add(CustomerAddressResponseDTO.from(address)));
            dto.setAddresses(addressDtoList);
        }
        return dto;
    }
}
