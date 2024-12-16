package com.epa.m.clean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommissionedEmployeeTest {

    CommissionedEmployee employee;

    @BeforeEach
    public void setUp() {
        employee = new CommissionedEmployee("John Doe", BigDecimal.valueOf(1000));
    }

    @Test
    public void testCalculatePay() {
        Money expectedPay = new Money(BigDecimal.valueOf(100)); // 10% of 1000
        Money actualPay = employee.calculatePay();
        assertEquals(expectedPay.amount(), actualPay.amount(), "The calculated pay should be correct");
    }

    @Test
    public void testCalculateBonus() {
        Money expectedBonus = new Money(BigDecimal.valueOf(5)); // 0.5% of 1000
        Money actualBonus = employee.calculateBonus();
        assertEquals(expectedBonus.amount(), actualBonus.amount(), "The calculated bonus should be correct");
    }
}