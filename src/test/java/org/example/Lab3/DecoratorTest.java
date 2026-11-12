package org.example.Lab3;

import org.example.Lab3.Beverage.Beverage;
import org.example.Lab3.Beverage.Espresso;
import org.example.Lab3.Beverage.Tea;
import org.example.Lab3.Decorator.ExtraShot;
import org.example.Lab3.Decorator.Milk;
import org.example.Lab3.Decorator.Syrup;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DecoratorTest {

    @Test
    void anyOrderSameCost() {
        Beverage milkThenSyrup = new Syrup(new Milk(new Espresso()));
        Beverage syrupThenMilk = new Milk(new Syrup(new Espresso()));

        assertEquals(3.10, milkThenSyrup.getCost(), 0.001);
        assertEquals(milkThenSyrup.getCost(), syrupThenMilk.getCost(), 0.001);
    }

    @Test
    void namesInOrder() {
        Beverage drink = new ExtraShot(new Milk(new Espresso()));

        assertEquals("Espresso, Milk, Extra Shot", drink.getDescription());
    }

    @Test
    void sameAddOnTwice() {
        Beverage doubleMilkTea = new Milk(new Milk(new Tea()));

        assertEquals("Tea, Milk, Milk", doubleMilkTea.getDescription());
        assertEquals(2.50, doubleMilkTea.getCost(), 0.001);
    }
}
