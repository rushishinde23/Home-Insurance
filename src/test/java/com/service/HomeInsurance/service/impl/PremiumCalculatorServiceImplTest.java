package com.service.HomeInsurance.service.impl;


import com.service.HomeInsurance.entity.HomePolicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PremiumCalculatorServiceImplTest {

    private PremiumCalculatorServiceImpl premiumCalculatorService;
    private HomePolicy homePolicy;

    @BeforeEach
    void setUp() {
        premiumCalculatorService = new PremiumCalculatorServiceImpl();
        homePolicy = new HomePolicy();
        homePolicy.setBuildingValue(1000000.00);
        homePolicy.setContentValue(500000.00);
        homePolicy.setEarthquakeCover(false);
        homePolicy.setFloodCover(false);
        homePolicy.setLocationRisk("LOW");
    }

    @Test
    void testCalculatePremiumBasic() {
        double premium = premiumCalculatorService.calculatePremium(homePolicy);
        assertEquals(2000.0, premium, 0.001);
    }

    @Test
    void testCalculatePremiumWithCovers() {
        homePolicy.setEarthquakeCover(true);
        homePolicy.setFloodCover(true);
        double premium = premiumCalculatorService.calculatePremium(homePolicy);
        assertEquals(3800.0, premium, 0.001);
    }

    @Test
    void testCalculatePremiumWithHighRisk() {
        homePolicy.setLocationRisk("HIGH");
        double premium = premiumCalculatorService.calculatePremium(homePolicy);
        assertEquals(2400.0, premium, 0.001);
    }

    @Test
    void testCalculatePremiumWithMediumRisk() {
        homePolicy.setLocationRisk("MEDIUM");
        double premium = premiumCalculatorService.calculatePremium(homePolicy);
        assertEquals(2200.0, premium, 0.001);
    }

    @Test
    void testCalculatePremiumAllAddOnsAndHighRisk() {
        homePolicy.setEarthquakeCover(true);
        homePolicy.setFloodCover(true);
        homePolicy.setLocationRisk("HIGH");
        double premium = premiumCalculatorService.calculatePremium(homePolicy);
        assertEquals(4560.0, premium, 0.001);
    }
}




