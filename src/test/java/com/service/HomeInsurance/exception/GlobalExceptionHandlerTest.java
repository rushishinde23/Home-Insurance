package com.service.HomeInsurance.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.HomeInsurance.controller.HomePolicyController;
import com.service.HomeInsurance.service.HomePolicyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class GlobalExceptionHandlerTest {

    private MockMvc mockMvc;

    @Mock
    private HomePolicyService homePolicyService;

    @InjectMocks
    private HomePolicyController homePolicyController;

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(homePolicyController)
                .setControllerAdvice(globalExceptionHandler)
                .build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testResourceNotFoundException() throws Exception {
        when(homePolicyService.getHomePolicyById(1L)).thenThrow(new ResourceNotFoundException("Policy not found"));

        mockMvc.perform(get("/api/homepolicy/{id}", 1L))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Policy not found"));
    }
}
