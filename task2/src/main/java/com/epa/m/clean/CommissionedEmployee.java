package com.epa.m.clean;

import java.math.BigDecimal;

public class CommissionedEmployee extends Employee {
    private static BigDecimal COMMSION_PERCENTAGE = BigDecimal.valueOf(10);
    private static BigDecimal BONUS_PERCENTAGE = BigDecimal.valueOf(0.5);
    private final BigDecimal sellingPrice;

    public CommissionedEmployee(String name, BigDecimal sellingPrice) {
        super(name);
        this.sellingPrice = sellingPrice;
    }

    @Override
    public Money calculatePay() {
        return new Money(COMMSION_PERCENTAGE.multiply(sellingPrice));
    }

    @Override
    public Money calculateBonus() {
        return new Money(BONUS_PERCENTAGE.multiply(sellingPrice));
    }
}
