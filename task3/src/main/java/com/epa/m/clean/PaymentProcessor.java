package com.epa.m.clean;

public class PaymentProcessor {
    public void makePayment(PaymentMethod payment, double amount) {
        if (payment.validatePaymentDetails()) {
            payment.processPayment(amount);
        }
    }
}

// PaymentMethod remains unchanged, serving as the component interface.
abstract class PaymentMethod {
    abstract boolean validatePaymentDetails();
    abstract void processPayment(double amount);
}

// Concrete Components
class CreditCardPayment extends PaymentMethod {
    @Override
    boolean validatePaymentDetails() {
        // Validate credit card details
        return true;
    }

    @Override
    void processPayment(double amount) {
        // Process credit card payment
    }
}

class PayPalPayment extends PaymentMethod {
    @Override
    boolean validatePaymentDetails() {
        // Validate PayPal account
        return true;
    }

    @Override
    void processPayment(double amount) {
        // Log in to PayPal and process payment
    }
}

// Decorator Base Class
abstract class PaymentDecorator extends PaymentMethod {
    protected PaymentMethod decoratedPaymentMethod;

    public PaymentDecorator(PaymentMethod paymentMethod) {
        this.decoratedPaymentMethod = paymentMethod;
    }

    boolean validatePaymentDetails() {
        return decoratedPaymentMethod.validatePaymentDetails();
    }

    void processPayment(double amount) {
        decoratedPaymentMethod.processPayment(amount);
    }
}

// Concrete Decorator
class BankAccountLinkedCheckDecorator extends PaymentDecorator {
    public BankAccountLinkedCheckDecorator(PaymentMethod paymentMethod) {
        super(paymentMethod);
    }

    @Override
    boolean validatePaymentDetails() {
        // Additional validation: check if bank account is linked
        return super.validatePaymentDetails() && isBankAccountLinked();
    }

    private boolean isBankAccountLinked() {
        // Check if bank account is linked
        return true; // This would actually check the linked bank account status
    }
}
