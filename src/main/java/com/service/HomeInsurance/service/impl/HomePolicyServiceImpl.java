package com.service.HomeInsurance.service.impl;

import com.service.HomeInsurance.dto.HomePolicyRequestDTO;
import com.service.HomeInsurance.dto.HomePolicyResponseDTO;
import com.service.HomeInsurance.entity.HomePolicy;
import com.service.HomeInsurance.exception.ResourceNotFoundException;
import com.service.HomeInsurance.mapper.HomePolicyMapper;
import com.service.HomeInsurance.repository.HomePolicyRepository;
import com.service.HomeInsurance.service.HomePolicyService;
import com.service.HomeInsurance.util.LocationRiskUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomePolicyServiceImpl implements HomePolicyService {

    private final HomePolicyRepository homePolicyRepository;
    private final PremiumCalculatorServiceImpl premiumCalculatorService;
    private final HomePolicyMapper policyMapper;
    private final LocationRiskUtil riskUtil;

    @Override
    public List<HomePolicyResponseDTO> getAllHomePolicies() {
        List<HomePolicy> policies=homePolicyRepository.findAll();
        return policies.stream().map(e->policyMapper.toResponseDto(e)).collect(Collectors.toList());

       /*  List<HomePolicyResponseDTO> responseDTOList=new ArrayList<>();
       for(HomePolicy policy:policies){
            HomePolicyResponseDTO responseDTO=policyMapper.toResponseDto(policy);
            responseDTOList.add(responseDTO);
        }
        return responseDTOList;*/
           }

    @Override
    public HomePolicyResponseDTO getHomePolicyById(Long policyId) {
        HomePolicy policy =getPolicyEntityById(policyId);
        return policyMapper.toResponseDto(policy);
    }

    public HomePolicy getPolicyEntityById(Long policyId){
        HomePolicy policy = homePolicyRepository.findById(policyId).orElseThrow(()->new ResourceNotFoundException("policy is not available with id:"+policyId));
        return policy;
    }


    @Override
    public HomePolicyResponseDTO addHomePolicy(HomePolicyRequestDTO requestDTO) {

        HomePolicy policy=policyMapper.toPolicy(requestDTO);

        policy.setLocationRisk(riskUtil.riskCalculator(requestDTO.getCity()));
        policy.setPremium(premiumCalculatorService.calculatePremium(policy));
        policy.setStartDate(LocalDate.now());
        policy.setEndDate(policy.getStartDate().plusYears(1));

        return policyMapper.toResponseDto(homePolicyRepository.save(policy));
    }

    @Override
    public HomePolicyResponseDTO getPremium(HomePolicyRequestDTO requestDTO) {
        HomePolicy policy=policyMapper.toPolicy(requestDTO);
        policy.setLocationRisk(riskUtil.riskCalculator(requestDTO.getCity()));
        policy.setPremium(premiumCalculatorService.calculatePremium(policy));
        policy.setStartDate(LocalDate.now());
        policy.setEndDate(policy.getStartDate().plusYears(1));
        policy.setPolicyId(1L);
        return policyMapper.toResponseDto(policy);

    }

    @Override
    public HomePolicyResponseDTO updateHomePolicy(Long policyId, HomePolicyRequestDTO requestDTO) {

          HomePolicy policy=policyMapper.toPolicy(requestDTO);
          policy.setPolicyId(policyId);

        policy.setLocationRisk(riskUtil.riskCalculator(requestDTO.getCity()));
        policy.setPremium(premiumCalculatorService.calculatePremium(policy));
        policy.setStartDate(LocalDate.now());
        policy.setEndDate(policy.getStartDate().plusYears(1));

        return policyMapper.toResponseDto(homePolicyRepository.save(policy));
    }

    @Override
    public String deleteHomePolicy(Long policyId) {

        homePolicyRepository.deleteById(policyId);
        return "Policy deleted successfully";
    }
}
