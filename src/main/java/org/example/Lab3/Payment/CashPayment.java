package org.example.Lab3.Payment;

// REAL SUBJECT: the customer pays with whole dollar bills and gets change.
public class CashPayment implements PaymentProcessor {
    @Override
    public String getName() {
        return "Cash";
    }

    @Override
    public boolean pay(double amount) {
        double given = Math.ceil(amount);
        System.out.printf("[Cash] Received $%.2f, change $%.2f.%n", given, given - amount);
        return true;
    }
}
