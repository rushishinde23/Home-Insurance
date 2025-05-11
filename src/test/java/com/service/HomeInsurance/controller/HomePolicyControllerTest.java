package com.service.HomeInsurance.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.HomeInsurance.dto.HomePolicyRequestDTO;
import com.service.HomeInsurance.dto.HomePolicyResponseDTO;
import com.service.HomeInsurance.service.HomePolicyService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HomePolicyController.class)
public class HomePolicyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HomePolicyService homePolicyService;  // 🔽 Dependency (Mocked)

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddHomePolicy() throws Exception {
        HomePolicyRequestDTO requestDTO = new HomePolicyRequestDTO();
        requestDTO.setCity("Pune");

        HomePolicyResponseDTO responseDTO = new HomePolicyResponseDTO();
        responseDTO.setPolicyId(1L);

        when(homePolicyService.addHomePolicy(any())).thenReturn(responseDTO);

        mockMvc.perform(post("/api/homepolicy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.policyId").value(1L));
    }

    @Test
    void testGetAllPolicies() throws Exception {
        HomePolicyResponseDTO responseDTO = new HomePolicyResponseDTO();
        responseDTO.setPolicyId(1L);

        when(homePolicyService.getAllHomePolicies())
                .thenReturn(Collections.singletonList(responseDTO));

        mockMvc.perform(get("/api/homepolicy"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    void testGetPolicyById() throws Exception {
        HomePolicyResponseDTO responseDTO = new HomePolicyResponseDTO();
        responseDTO.setPolicyId(1L);

        when(homePolicyService.getHomePolicyById(1L)).thenReturn(responseDTO);

        mockMvc.perform(get("/api/homepolicy/1"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.policyId").value(1L));
    }

    @Test
    void testDeletePolicy() throws Exception {
        when(homePolicyService.deleteHomePolicy(1L)).thenReturn("Policy deleted successfully");

        mockMvc.perform(delete("/api/homepolicy/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Policy deleted successfully"));
    }

    @Test
    void testCalculatePremium() throws Exception {
        HomePolicyRequestDTO requestDTO = new HomePolicyRequestDTO();
        requestDTO.setCity("Pune");

        HomePolicyResponseDTO responseDTO = new HomePolicyResponseDTO();
        responseDTO.setPolicyId(1L);

        when(homePolicyService.getPremium(any())).thenReturn(responseDTO);

        mockMvc.perform(post("/api/homepolicy/calculatePremium")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.policyId").value(1L));
    }

    @Test
    void testUpdatePolicy() throws Exception {
        // Given
        HomePolicyRequestDTO requestDTO = new HomePolicyRequestDTO();
        requestDTO.setPolicyHolderName("John Doe Updated");
        requestDTO.setCity("Mumbai");
        requestDTO.setBuildingValue(600000.0);
        requestDTO.setContentValue(150000.0);
        requestDTO.setCarpetArea(160.0);
        requestDTO.setBuildingType("Wooden");
        requestDTO.setEarthquakeCover(true);
        requestDTO.setFloodCover(true);

        HomePolicyResponseDTO responseDTO = new HomePolicyResponseDTO();
        responseDTO.setPolicyId(1L);
        responseDTO.setPolicyHolderName("John Doe Updated");

        when(homePolicyService.updateHomePolicy(1L, requestDTO)).thenReturn(responseDTO);

        // When & Then
        mockMvc.perform(put("/api/homepolicy/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.policyId").value(1L))
                .andExpect(jsonPath("$.policyHolderName").value("John Doe Updated"));
    }

}