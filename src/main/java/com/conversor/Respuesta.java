package com.conversor;
import java.util.Map;

public class Respuesta {
    private String base_code;
    private Map<String, Double> conversion_rates;

    public Map<String, Double> getConversion_rates() {
        return conversion_rates;
    }
}
