package com.service.HomeInsurance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomePolicyRequestDTO {
    private String policyHolderName;
    private String city;
    private Double buildingValue;
    private Double contentValue;
    private Double carpetArea;
    private String buildingType;
    private Boolean earthquakeCover;
    private Boolean floodCover;

}
