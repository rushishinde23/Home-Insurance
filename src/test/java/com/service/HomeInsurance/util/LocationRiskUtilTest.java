package com.service.HomeInsurance.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationRiskUtilTest {

    private LocationRiskUtil riskUtil;

    @BeforeEach
    void setUp() {
        riskUtil = new LocationRiskUtil();
    }

    @Test
    void testRiskCalculatorForLowRiskCity() {
        String risk = riskUtil.riskCalculator("Pune");
        assertEquals("LOW", risk);
    }

    @Test
    void testRiskCalculatorForHighRiskCity() {
        String risk = riskUtil.riskCalculator("Mumbai");
        assertEquals("HIGH", risk);
    }

    @Test
    void testRiskCalculatorForMediumRiskCity() {
        String risk = riskUtil.riskCalculator("Chennai"); // Not in LOW or HIGH lists
        assertEquals("MEDIUM", risk);
    }

    @Test
    void testRiskCalculatorWithCaseInsensitiveCityName() {
        String risk = riskUtil.riskCalculator("nAgPuR");
        assertEquals("LOW", risk);
    }

    @Test
    void testRiskCalculatorForNullOrEmptyCity() {
        String riskEmpty = riskUtil.riskCalculator("");
        assertEquals("MEDIUM", riskEmpty);

        String riskNull = riskUtil.riskCalculator(null);
        assertEquals("MEDIUM", riskNull);
    }
}
