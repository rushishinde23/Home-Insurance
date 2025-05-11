package com.service.HomeInsurance.controller;

import com.service.HomeInsurance.dto.HomePolicyRequestDTO;
import com.service.HomeInsurance.dto.HomePolicyResponseDTO;
import com.service.HomeInsurance.service.HomePolicyService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HomePolicyController {

    private final HomePolicyService homePolicyService;

    @PostMapping("/homepolicy/calculatePremium")
    public ResponseEntity<HomePolicyResponseDTO> getCalculatedPremium(@RequestBody HomePolicyRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(homePolicyService.getPremium(requestDTO));
    }

    @GetMapping("/homepolicy")
    public ResponseEntity<List<HomePolicyResponseDTO>> getAllPolicies(){
        return ResponseEntity.status(HttpStatus.OK).body(homePolicyService.getAllHomePolicies());
    }

    @GetMapping("/homepolicy/{id}")
    public ResponseEntity<HomePolicyResponseDTO> getPolicyById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(homePolicyService.getHomePolicyById(id));
    }

    @PostMapping("/homepolicy")
    public ResponseEntity<HomePolicyResponseDTO> addPolicy(@RequestBody HomePolicyRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(homePolicyService.addHomePolicy(requestDTO));
    }

    @PutMapping("homepolicy/{id}")
    public ResponseEntity<HomePolicyResponseDTO> updatePolicy(@PathVariable Long id,@RequestBody HomePolicyRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(homePolicyService.updateHomePolicy(id,requestDTO));
    }

    @DeleteMapping("/homepolicy/{id}")
    public ResponseEntity<String> deletePolicy(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(homePolicyService.deleteHomePolicy(id));
    }
}
