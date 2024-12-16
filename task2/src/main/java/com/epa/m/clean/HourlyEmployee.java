package com.epa.m.clean;

import java.math.BigDecimal;

public class HourlyEmployee extends Employee {

        private final Integer hours;
        private final BigDecimal salaryPerHour;
        private final BigDecimal bonus;

    public HourlyEmployee(String name, Integer hours, BigDecimal salaryPerHour, BigDecimal bonus ) {
            super(name);
            this.hours = hours;
            this.salaryPerHour = salaryPerHour;
            this.bonus = bonus;
        }

        @Override
        public Money calculatePay() {
            return new Money(salaryPerHour.multiply(BigDecimal.valueOf(hours)));
        }

        @Override
        public Money calculateBonus() {
            return new Money(bonus.multiply(BigDecimal.valueOf(hours)));
        }
}

