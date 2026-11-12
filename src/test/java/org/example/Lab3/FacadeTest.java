package org.example.Lab3;

import org.example.Lab3.Beverage.Beverage;
import org.example.Lab3.Beverage.Espresso;
import org.example.Lab3.Facade.Barista;
import org.example.Lab3.Facade.CoffeeShopFacade;
import org.example.Lab3.Facade.ReceiptPrinter;
import org.example.Lab3.Factory.PaymentFactory;
import org.example.Lab3.Payment.PaymentProcessor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FacadeTest {

    private final List<String> calls = new ArrayList<>();

    // stubs that only record that they were called
    private final Barista barista = new Barista() {
        public void prepare(Beverage beverage) { calls.add("prepare"); }
    };
    private final ReceiptPrinter printer = new ReceiptPrinter() {
        public void print(Beverage beverage, String paymentName) { calls.add("print"); }
    };

    private PaymentFactory paymentThatReturns(boolean result) {
        return new PaymentFactory() {
            public String getName() { return "Stub"; }

            public PaymentProcessor create() {
                return new PaymentProcessor() {
                    public String getName() { return "Stub"; }

                    public boolean pay(double amount) {
                        calls.add("pay");
                        return result;
                    }
                };
            }
        };
    }

    @Test
    void callsInOrder() {
        CoffeeShopFacade shop = new CoffeeShopFacade(barista, printer);

        assertTrue(shop.placeOrder(new Espresso(), paymentThatReturns(true)));
        assertEquals(List.of("pay", "prepare", "print"), calls);
    }

    @Test
    void stopsIfPaymentFails() {
        CoffeeShopFacade shop = new CoffeeShopFacade(barista, printer);

        assertFalse(shop.placeOrder(new Espresso(), paymentThatReturns(false)));
        assertEquals(List.of("pay"), calls, "barista and printer must not be called");
    }
}
