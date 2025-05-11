package com.service.HomeInsurance.mapper;

import com.service.HomeInsurance.dto.HomePolicyRequestDTO;
import com.service.HomeInsurance.dto.HomePolicyResponseDTO;
import com.service.HomeInsurance.entity.HomePolicy;
import org.springframework.stereotype.Component;

@Component
public class HomePolicyMapper {

    public HomePolicy toPolicy(HomePolicyRequestDTO requestDTO){
        HomePolicy policy=new HomePolicy();

        policy.setPolicyHolderName(requestDTO.getPolicyHolderName());
        policy.setCity(requestDTO.getCity());
        policy.setBuildingValue(requestDTO.getBuildingValue());
        policy.setContentValue(requestDTO.getContentValue());
        policy.setCarpetArea(requestDTO.getCarpetArea());
        policy.setBuildingType(requestDTO.getBuildingType());
        policy.setEarthquakeCover(requestDTO.getEarthquakeCover());
        policy.setFloodCover(requestDTO.getFloodCover());

        return policy;
    }

    public HomePolicyResponseDTO toResponseDto(HomePolicy policy){
        HomePolicyResponseDTO responseDTO=new HomePolicyResponseDTO();

        responseDTO.setPolicyId(policy.getPolicyId());
        responseDTO.setPolicyHolderName(policy.getPolicyHolderName());
        responseDTO.setLocationRisk(policy.getLocationRisk());
        responseDTO.setPremium(policy.getPremium());
        responseDTO.setStartDate(policy.getStartDate());
        responseDTO.setEndDate(policy.getEndDate());

        return  responseDTO;
    }
}
