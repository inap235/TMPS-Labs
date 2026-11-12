package org.example.Lab3.Factory;

import org.example.Lab3.Payment.CardPayment;
import org.example.Lab3.Payment.PaymentProcessor;
import org.example.Lab3.Proxy.PaymentLimitProxy;

public class CardPaymentFactory implements PaymentFactory {
    private static final double CARD_LIMIT = 50.00;

    @Override
    public String getName() {
        return "Card";
    }

    @Override
    public PaymentProcessor create() {
        return new PaymentLimitProxy(new CardPayment(), CARD_LIMIT);
    }
}
