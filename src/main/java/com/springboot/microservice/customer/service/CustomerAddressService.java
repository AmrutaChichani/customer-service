package com.springboot.microservice.customer.service;

import com.springboot.microservice.customer.dao.CustomerAddressDAO;
import com.springboot.microservice.customer.dto.CustomerAddressDTO;
import com.springboot.microservice.customer.entity.CustomerAddress;
import com.springboot.microservice.customer.exception.InvalidAddressIdException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerAddressService {

    @Autowired
    private CustomerAddressDAO customerAddressDao;

    @Autowired
    private CustomerService customerServices;

    public Integer addOrUpdateAddress(CustomerAddressDTO address)
    {
        CustomerAddress theAddress=new CustomerAddress();
        if(address.getId()!=null)
            theAddress.setId(address.getId());
        theAddress.setCity(address.getCity());
        theAddress.setLocality(address.getLocality());
        theAddress.setState(address.getState());
        theAddress.setCountry(address.getCountry());
        theAddress.setPinCode(address.getPinCode());
        theAddress.setCustomerId(customerServices.findByCustomerId(address.getCustomerId()));

        customerAddressDao.save(theAddress);
        return theAddress.getId();
    }

    public void deleteAddress(Integer addressId) {
        if (!customerAddressDao.existsById(addressId)) {
            log.info("Invalid Address id:{}", addressId);
            throw new InvalidAddressIdException();
        }
        customerAddressDao.deleteById(addressId);
    }
}
