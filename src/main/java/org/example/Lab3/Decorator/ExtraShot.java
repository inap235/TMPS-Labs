package org.example.Lab3.Decorator;

import org.example.Lab3.Beverage.Beverage;

public class ExtraShot extends AddOnDecorator {
    public ExtraShot(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Extra Shot";
    }

    @Override
    public double getCost() {
        return beverage.getCost() + 0.80;
    }
}
