package com.springboot.microservice.customer.dto;

import com.springboot.microservice.customer.entity.Customer;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Data
public class CustomerDTO {
    private Integer id;

    @NotEmpty
    private String name;
    @Email
    private String email;

    @Pattern(regexp = "^[0-9]{10}$",message = "only numeric value of 10 digits")
    private String contactNo;

    private List<Integer> addresses;

    public static CustomerDTO from(Customer customer){
        CustomerDTO dto=new CustomerDTO();
        if(customer.getId()!=null)
            dto.setId(customer.getId());

        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setContactNo(customer.getContactNo());
        if(customer.getCustomerAddresses()!=null) {
            List<Integer> addressDtoList = new ArrayList<>();
            customer.getCustomerAddresses().forEach(address ->
                    addressDtoList.add(address.getId()));
            dto.setAddresses(addressDtoList);
        }
        return dto;
    }
}
