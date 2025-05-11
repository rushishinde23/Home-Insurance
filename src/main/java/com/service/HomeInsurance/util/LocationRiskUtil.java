package com.service.HomeInsurance.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LocationRiskUtil {
     private static final Map<String, List<String>> locationMap=new HashMap<>();
    static {
        locationMap.put("LOW",List.of("PUNE","INDORE","NAGPUR","PARBHANI","BANGLORE","HYDERABAD","NANDED"));
        locationMap.put("HIGH",List.of("LATUR","KOLKATA","DELHI","MUMBAI","SHIMLA","MANALI","GUWAHATI","SHILONG","RISHIKESH"));
    }

    public String riskCalculator(String city){
        for(Map.Entry<String,List<String>> entry:locationMap.entrySet()){
            if(entry.getValue().stream().anyMatch(c->c.equalsIgnoreCase(city))){
                return entry.getKey();
            }
        }
        return "MEDIUM";
    }
}
