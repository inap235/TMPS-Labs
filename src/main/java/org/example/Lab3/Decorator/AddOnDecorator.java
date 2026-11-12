package org.example.Lab3.Decorator;

import org.example.Lab3.Beverage.Beverage;

// DECORATOR: is a Beverage AND has a Beverage it wraps.
// Each add-on adds its own text and price on top of the wrapped drink.
public abstract class AddOnDecorator implements Beverage {
    protected final Beverage beverage;

    protected AddOnDecorator(Beverage beverage) {
        this.beverage = beverage;
    }
}
