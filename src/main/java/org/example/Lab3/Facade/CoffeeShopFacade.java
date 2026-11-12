package org.example.Lab3.Facade;

import org.example.Lab3.Beverage.Beverage;
import org.example.Lab3.Factory.PaymentFactory;
import org.example.Lab3.Payment.PaymentProcessor;

// FACADE: one simple method that hides the whole ordering process.
// It only delegates: payment -> barista -> receipt. The client does not
// need to know about the proxy, the payment classes, the barista or the printer.
public class CoffeeShopFacade {
    private final Barista barista;
    private final ReceiptPrinter receiptPrinter;

    public CoffeeShopFacade(Barista barista, ReceiptPrinter receiptPrinter) {
        this.barista = barista;
        this.receiptPrinter = receiptPrinter;
    }

    public boolean placeOrder(Beverage beverage, PaymentFactory paymentFactory) {
        System.out.printf("%nNew order: %s ($%.2f)%n", beverage.getDescription(), beverage.getCost());

        PaymentProcessor payment = paymentFactory.create();
        if (!payment.pay(beverage.getCost())) {
            System.out.println("Payment failed. Order cancelled.");
            return false;
        }

        barista.prepare(beverage);
        receiptPrinter.print(beverage, payment.getName());
        return true;
    }
}
