package com.epa.m.clean;

import java.math.BigDecimal;

public class SalariedEmployee extends Employee {
    private final static BigDecimal BONUS = new BigDecimal("0.5");
    private final static BigDecimal MONTHS = new BigDecimal("12");
    private final BigDecimal salary;

    public SalariedEmployee(String name, BigDecimal salary ) {
        super(name);
        this.salary = salary;
    }

    @Override
    public Money calculatePay() {
        return new Money(salary);
    }

    @Override
    public Money calculateBonus() {
        return new Money(BONUS.multiply(salary).multiply(MONTHS));
    }
}