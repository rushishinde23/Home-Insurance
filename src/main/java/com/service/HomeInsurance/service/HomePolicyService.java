package com.service.HomeInsurance.service;

import com.service.HomeInsurance.dto.HomePolicyRequestDTO;
import com.service.HomeInsurance.dto.HomePolicyResponseDTO;

import java.util.List;

public interface HomePolicyService {

     List<HomePolicyResponseDTO> getAllHomePolicies();
     HomePolicyResponseDTO getHomePolicyById(Long policyId);
     HomePolicyResponseDTO addHomePolicy(HomePolicyRequestDTO requestDTO);
     HomePolicyResponseDTO getPremium(HomePolicyRequestDTO requestDTO);
     HomePolicyResponseDTO updateHomePolicy(Long policyId,HomePolicyRequestDTO requestDTO);
     String deleteHomePolicy (Long policyId);

}
