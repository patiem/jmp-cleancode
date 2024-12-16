package com.epa.m.clean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HourlyEmployeeTest {

    HourlyEmployee employee;

    @BeforeEach
    public void setUp() {
        employee = new HourlyEmployee("Jane Doe", 40, BigDecimal.valueOf(25), BigDecimal.valueOf(2));
    }

    @Test
    public void testCalculatePay() {
        Money expectedPay = new Money(BigDecimal.valueOf(1000)); // 25 per hour * 40 hours
        Money actualPay = employee.calculatePay();
        assertEquals(expectedPay.amount(), actualPay.amount(), "The calculated pay should be correct");
    }

    @Test
    public void testCalculateBonus() {
        Money expectedBonus = new Money(BigDecimal.valueOf(80)); // 2 per hour * 40 hours
        Money actualBonus = employee.calculateBonus();
        assertEquals(expectedBonus.amount(), actualBonus.amount(), "The calculated bonus should be correct");
    }
}