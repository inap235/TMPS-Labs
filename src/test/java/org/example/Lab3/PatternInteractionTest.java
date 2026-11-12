package org.example.Lab3;

import org.example.Lab3.Beverage.Beverage;
import org.example.Lab3.Beverage.Espresso;
import org.example.Lab3.Decorator.Milk;
import org.example.Lab3.Decorator.Syrup;
import org.example.Lab3.Facade.Barista;
import org.example.Lab3.Facade.CoffeeShopFacade;
import org.example.Lab3.Facade.ReceiptPrinter;
import org.example.Lab3.Factory.CardPaymentFactory;
import org.example.Lab3.Factory.CashPaymentFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Facade -> Factory -> Proxy -> real payment, with a decorated drink
class PatternInteractionTest {

    private final CoffeeShopFacade shop = new CoffeeShopFacade(new Barista(), new ReceiptPrinter());

    @Test
    void decoratedDrinkOverCashLimitIsBlockedByProxy() {
        Beverage drink = new Syrup(new Milk(new Espresso()));   // $3.10 > $3.00 cash limit

        assertFalse(shop.placeOrder(drink, new CashPaymentFactory()));
    }

    @Test
    void sameDrinkCanBePaidByCard() {
        Beverage drink = new Syrup(new Milk(new Espresso()));   // $3.10 < $50.00 card limit

        assertTrue(shop.placeOrder(drink, new CardPaymentFactory()));
    }
}
