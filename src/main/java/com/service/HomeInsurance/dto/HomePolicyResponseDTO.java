package com.service.HomeInsurance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomePolicyResponseDTO {
    private Long policyId;
    private String policyHolderName;
    private String locationRisk;
    private Double premium;
    private LocalDate startDate;
    private LocalDate endDate;
}
