package com.service.HomeInsurance.service.impl;

import com.service.HomeInsurance.entity.HomePolicy;
import org.springframework.stereotype.Service;

@Service
public class PremiumCalculatorServiceImpl {

    public double calculatePremium(HomePolicy homePolicy){

        double premium=homePolicy.getBuildingValue()*0.001;
        premium+=homePolicy.getContentValue()*0.002;

        if(homePolicy.getEarthquakeCover()){
            premium+=1000;
        }
        if(homePolicy.getFloodCover()){
            premium+=800;
        }

        if("HIGH".equals(homePolicy.getLocationRisk())){
            premium+=premium*0.2;
        }
        else if("MEDIUM".equals(homePolicy.getLocationRisk())){
            premium+=premium*0.1;
        }

        return premium;
    }
}
