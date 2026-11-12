package org.example.Lab3.Factory;

import org.example.Lab3.Payment.CashPayment;
import org.example.Lab3.Payment.PaymentProcessor;
import org.example.Lab3.Proxy.PaymentLimitProxy;

public class CashPaymentFactory implements PaymentFactory {
    // the register keeps little change, so big cash orders are not accepted
    private static final double CASH_LIMIT = 3.00;

    @Override
    public String getName() {
        return "Cash";
    }

    @Override
    public PaymentProcessor create() {
        return new PaymentLimitProxy(new CashPayment(), CASH_LIMIT);
    }
}
