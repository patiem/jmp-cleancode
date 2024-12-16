package com.epa.m.clean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentService {

    public Map<Employee, Money> createPaymentList(List<Employee> employees){
        Map<Employee, Money> paymentList = new HashMap<>();
        employees.forEach(employee -> paymentList.put(employee, employee.calculatePay()));
        return paymentList;
    }

    public Map<Employee, Money> createBonusList(List<Employee> employees){
        Map<Employee, Money> paymentList = new HashMap<>();
        employees.forEach(employee -> paymentList.put(employee, employee.calculateBonus()));
        return paymentList;
    }

    public Map<Employee, Money> createWholePayrollList(List<Employee> employees){
        Map<Employee, Money> paymentList = new HashMap<>();
        employees.forEach(employee -> paymentList.put(employee,
                new Money(employee.calculatePay().amount().add(employee.calculateBonus().amount()))));
        return paymentList;
    }
}
