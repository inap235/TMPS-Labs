package org.example.Lab3.Payment;

// SUBJECT: the common interface for real payments AND their proxy.
public interface PaymentProcessor {
    String getName();

    boolean pay(double amount);
}
