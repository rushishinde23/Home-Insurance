package com.service.HomeInsurance.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException() {
        super("Policy not found!!!");
    }

    public ResourceNotFoundException(String message){
        super(message);
    }
}
