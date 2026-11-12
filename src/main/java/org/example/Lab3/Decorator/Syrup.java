package org.example.Lab3.Decorator;

import org.example.Lab3.Beverage.Beverage;

public class Syrup extends AddOnDecorator {
    public Syrup(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Syrup";
    }

    @Override
    public double getCost() {
        return beverage.getCost() + 0.60;
    }
}
