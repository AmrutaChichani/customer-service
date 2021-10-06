package com.springboot.microservice.customer.exception;

public class InvalidAddressIdException extends RuntimeException{

    public InvalidAddressIdException() {
        super("Invalid customer address id");
    }
}
