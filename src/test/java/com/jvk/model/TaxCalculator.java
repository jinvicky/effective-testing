package com.jvk.model;

public class TaxCalculator {
    public double calculateTxt(double value) {
        assert value >= 0 : "Value cannot be negative";

        double taxValue = 0;

        // 복잡한 비즈니스 로직 작성, taxValue에 값 업데이트

        assert taxValue >= 0 : "Calculated tax value cannot be negative";
        return taxValue;
    }
}
