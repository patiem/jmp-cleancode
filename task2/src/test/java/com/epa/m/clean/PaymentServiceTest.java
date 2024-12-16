package com.epa.m.clean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PaymentServiceTest {

    private PaymentService paymentService;
    private Employee employee1;
    private Employee employee2;

    @BeforeEach
    public void setUp() {
        paymentService = new PaymentService();
        employee1 = mock(Employee.class);
        employee2 = mock(Employee.class);

        when(employee1.calculatePay()).thenReturn(new Money(BigDecimal.valueOf(1000)));
        when(employee1.calculateBonus()).thenReturn(new Money(BigDecimal.valueOf(100)));
        when(employee2.calculatePay()).thenReturn(new Money(BigDecimal.valueOf(2000)));
        when(employee2.calculateBonus()).thenReturn(new Money(BigDecimal.valueOf(200)));
    }

    @Test
    public void testCreatePaymentList() {
        List<Employee> employees = Arrays.asList(employee1, employee2);
        Map<Employee, Money> paymentList = paymentService.createPaymentList(employees);

        assertEquals(BigDecimal.valueOf(1000), paymentList.get(employee1).amount());
        assertEquals(BigDecimal.valueOf(2000), paymentList.get(employee2).amount());
    }

    @Test
    public void testCreateBonusList() {
        List<Employee> employees = Arrays.asList(employee1, employee2);
        Map<Employee, Money> bonusList = paymentService.createBonusList(employees);

        assertEquals(BigDecimal.valueOf(100), bonusList.get(employee1).amount());
        assertEquals(BigDecimal.valueOf(200), bonusList.get(employee2).amount());
    }

    @Test
    public void testCreateWholePayrollList() {
        List<Employee> employees = Arrays.asList(employee1, employee2);
        Map<Employee, Money> payrollList = paymentService.createWholePayrollList(employees);

        assertEquals(BigDecimal.valueOf(1100), payrollList.get(employee1).amount());
        assertEquals(BigDecimal.valueOf(2200), payrollList.get(employee2).amount());
    }
}