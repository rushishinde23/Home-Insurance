package com.service.HomeInsurance.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceNotFoundExceptionTest {

    @Test
    void testDefaultConstructor() {
        ResourceNotFoundException exception = new ResourceNotFoundException();

        assertEquals("Policy not found!!!", exception.getMessage());
    }

    @Test
    void testConstructorWithCustomMessage() {
        String customMessage = "Policy with the given ID does not exist!";
        ResourceNotFoundException exception = new ResourceNotFoundException(customMessage);

        assertEquals(customMessage, exception.getMessage());
    }
}
