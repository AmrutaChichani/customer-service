package com.springboot.microservice.customer.exception;

public class InvalidCustomerIdException extends RuntimeException {

    public  InvalidCustomerIdException(){
        super("Invalid Customer id");
    }
}
