package com.service.HomeInsurance.mapper;

import com.service.HomeInsurance.dto.HomePolicyRequestDTO;
import com.service.HomeInsurance.dto.HomePolicyResponseDTO;
import com.service.HomeInsurance.entity.HomePolicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePolicyMapperTest {

    private HomePolicyMapper homePolicyMapper;

    @BeforeEach
    void setUp() {
        homePolicyMapper = new HomePolicyMapper();
    }

    @Test
    void testToPolicy() {
        // Given
        HomePolicyRequestDTO requestDTO = new HomePolicyRequestDTO();
        requestDTO.setPolicyHolderName("John Doe");
        requestDTO.setCity("Pune");
        requestDTO.setBuildingValue(500000.0);
        requestDTO.setContentValue(100000.0);
        requestDTO.setCarpetArea(150.0);
        requestDTO.setBuildingType("Concrete");
        requestDTO.setEarthquakeCover(true);
        requestDTO.setFloodCover(false);

        // When
        HomePolicy policy = homePolicyMapper.toPolicy(requestDTO);

        // Then
        assertEquals("John Doe", policy.getPolicyHolderName());
        assertEquals("Pune", policy.getCity());
        assertEquals(500000.0, policy.getBuildingValue());
        assertEquals(100000.0, policy.getContentValue());
        assertEquals(150.0, policy.getCarpetArea());
        assertEquals("Concrete", policy.getBuildingType());
        assertEquals(true, policy.getEarthquakeCover());
        assertEquals(false, policy.getFloodCover());
    }

    @Test
    void testToResponseDto() {
        // Given
        HomePolicy policy = new HomePolicy();
        policy.setPolicyId(1L);
        policy.setPolicyHolderName("John Doe");
        policy.setLocationRisk("HIGH");
        policy.setPremium(2000.0);
        policy.setStartDate(java.time.LocalDate.now());
        policy.setEndDate(java.time.LocalDate.now().plusYears(1));

        // When
        HomePolicyResponseDTO responseDTO = homePolicyMapper.toResponseDto(policy);

        // Then
        assertEquals(1L, responseDTO.getPolicyId());
        assertEquals("John Doe", responseDTO.getPolicyHolderName());
        assertEquals("HIGH", responseDTO.getLocationRisk());
        assertEquals(2000.0, responseDTO.getPremium());
        assertEquals(policy.getStartDate(), responseDTO.getStartDate());
        assertEquals(policy.getEndDate(), responseDTO.getEndDate());
    }
}
