package com.epa.m.clean;

public abstract class Employee {
    protected String name;

    public Employee(String name) {
        this.name = name;
    }

    public abstract Money calculatePay();
    public abstract Money calculateBonus();
}