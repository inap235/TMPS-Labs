package org.example.Lab3.Payment;

// REAL SUBJECT: pays the exact amount by card.
public class CardPayment implements PaymentProcessor {
    @Override
    public String getName() {
        return "Card";
    }

    @Override
    public boolean pay(double amount) {
        System.out.printf("[Card] Charged $%.2f.%n", amount);
        return true;
    }
}
