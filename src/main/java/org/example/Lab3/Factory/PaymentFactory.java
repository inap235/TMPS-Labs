package org.example.Lab3.Factory;

import org.example.Lab3.Payment.PaymentProcessor;

// FACTORY METHOD (creational): each payment type has its own factory.
// A new payment type = a new payment class + a new factory, no existing class changes.
public interface PaymentFactory {
    String getName();

    PaymentProcessor create();
}
