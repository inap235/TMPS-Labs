package org.example.Lab3.Proxy;

import org.example.Lab3.Payment.PaymentProcessor;

// PROXY: has the same interface as the real payment and controls access to it.
// Payments above the limit are blocked; all others are passed through unchanged.
public class PaymentLimitProxy implements PaymentProcessor {
    private final PaymentProcessor realPayment;
    private final double limit;

    public PaymentLimitProxy(PaymentProcessor realPayment, double limit) {
        this.realPayment = realPayment;
        this.limit = limit;
    }

    @Override
    public String getName() {
        return realPayment.getName();
    }

    @Override
    public boolean pay(double amount) {
        if (amount > limit) {
            System.out.printf("[Proxy] Blocked: $%.2f is over the %s limit of $%.2f.%n",
                    amount, realPayment.getName(), limit);
            return false;
        }
        return realPayment.pay(amount);
    }
}
