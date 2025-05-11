package com.service.HomeInsurance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomePolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long policyId;
    private String policyHolderName;
    private String city;
    private Double buildingValue;
    private Double contentValue;
    private Double carpetArea;
    private String buildingType;
    private Boolean earthquakeCover;
    private Boolean floodCover;
    private String locationRisk;
    private Double premium;
    private LocalDate startDate;
    private LocalDate endDate;

}
