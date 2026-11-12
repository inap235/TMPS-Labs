package org.example.Lab3;

import org.example.Lab3.Payment.PaymentProcessor;
import org.example.Lab3.Proxy.PaymentLimitProxy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProxyTest {

    // stub real payment that remembers what it received
    static class RecordingPayment implements PaymentProcessor {
        int calls = 0;
        double lastAmount = -1;

        public String getName() { return "Test"; }

        public boolean pay(double amount) {
            calls++;
            lastAmount = amount;
            return true;
        }
    }

    @Test
    void blocksOverLimit() {
        RecordingPayment real = new RecordingPayment();
        PaymentProcessor proxy = new PaymentLimitProxy(real, 3.00);

        assertFalse(proxy.pay(3.10));
        assertEquals(0, real.calls, "the real payment must not be called");
    }

    @Test
    void passesUnchanged() {
        RecordingPayment real = new RecordingPayment();
        PaymentProcessor proxy = new PaymentLimitProxy(real, 3.00);

        assertTrue(proxy.pay(2.50));
        assertEquals(1, real.calls);
        assertEquals(2.50, real.lastAmount, 0.001);
    }

    @Test
    void proxyPaymentInterfaceMatch() {
        PaymentProcessor proxy = new PaymentLimitProxy(new RecordingPayment(), 3.00);

        assertEquals("Test", proxy.getName());
    }
}
