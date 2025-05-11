package com.service.HomeInsurance.service.impl;

import com.service.HomeInsurance.dto.HomePolicyRequestDTO;
import com.service.HomeInsurance.dto.HomePolicyResponseDTO;
import com.service.HomeInsurance.entity.HomePolicy;
import com.service.HomeInsurance.exception.ResourceNotFoundException;
import com.service.HomeInsurance.mapper.HomePolicyMapper;
import com.service.HomeInsurance.repository.HomePolicyRepository;
import com.service.HomeInsurance.service.HomePolicyService;
import com.service.HomeInsurance.util.LocationRiskUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class HomePolicyServiceImplTest {


    @Mock
    private HomePolicyRepository homePolicyRepository;
    @Mock
    private PremiumCalculatorServiceImpl premiumCalculatorService;
    @Mock
    private HomePolicyMapper policyMapper;
    @Mock
    private LocationRiskUtil riskUtil;

    @InjectMocks
    private  HomePolicyServiceImpl homePolicyService;


    private HomePolicy policy;
    private HomePolicyRequestDTO requestDTO;
    private HomePolicyResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        policy = new HomePolicy();
        policy.setPolicyId(1L);
        policy.setCity("Pune");
        policy.setStartDate(LocalDate.now());
        policy.setEndDate(policy.getStartDate().plusYears(1));

        requestDTO = new HomePolicyRequestDTO();
        requestDTO.setCity("Pune");

        responseDTO = new HomePolicyResponseDTO();
        responseDTO.setPolicyId(1L);

    }

    @Test
    public void PolicyServiceImpl_getPremium_ReturnedResponseDto(){
        when(homePolicyRepository.findAll()).thenReturn(Arrays.asList(policy));
        when(policyMapper.toResponseDto(policy)).thenReturn(responseDTO);
        assertEquals(1, homePolicyService.getAllHomePolicies().size());
    }

    @Test
    void testGetHomePolicyById() {
        when(homePolicyRepository.findById(1L)).thenReturn(Optional.of(policy));
        when(policyMapper.toResponseDto(policy)).thenReturn(responseDTO);

        HomePolicyResponseDTO result = homePolicyService.getHomePolicyById(1L);
        assertEquals(1L, result.getPolicyId());
    }

    @Test
    void testGetHomePolicyByIdThrowsException() {
        when(homePolicyRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> homePolicyService.getHomePolicyById(1L));
    }

    @Test
    void testAddHomePolicy() {
        when(policyMapper.toPolicy(requestDTO)).thenReturn(policy);
        when(riskUtil.riskCalculator("Pune")).thenReturn("HIGH");
        when(premiumCalculatorService.calculatePremium(any())).thenReturn(5000.0);
        when(homePolicyRepository.save(any())).thenReturn(policy);
        when(policyMapper.toResponseDto(policy)).thenReturn(responseDTO);

        HomePolicyResponseDTO result = homePolicyService.addHomePolicy(requestDTO);
        assertNotNull(result);
    }

    @Test
    void testGetPremium() {
        when(policyMapper.toPolicy(requestDTO)).thenReturn(policy);
        when(riskUtil.riskCalculator("Pune")).thenReturn("HIGH");
        when(premiumCalculatorService.calculatePremium(any())).thenReturn(5000.0);
        when(policyMapper.toResponseDto(any())).thenReturn(responseDTO);

        HomePolicyResponseDTO result = homePolicyService.getPremium(requestDTO);
        assertEquals(1L, result.getPolicyId());
    }

    @Test
    void testUpdateHomePolicy() {
        when(policyMapper.toPolicy(requestDTO)).thenReturn(policy);
        when(riskUtil.riskCalculator("Pune")).thenReturn("HIGH");
        when(premiumCalculatorService.calculatePremium(any())).thenReturn(6000.0);
        when(homePolicyRepository.save(any())).thenReturn(policy);
        when(policyMapper.toResponseDto(policy)).thenReturn(responseDTO);

        HomePolicyResponseDTO result = homePolicyService.updateHomePolicy(1L, requestDTO);
        assertNotNull(result);
    }

    @Test
    void testDeleteHomePolicy() {
        doNothing().when(homePolicyRepository).deleteById(1L);
        String message = homePolicyService.deleteHomePolicy(1L);
        assertEquals("Policy deleted successfully", message);
    }



}