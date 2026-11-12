package org.example.Lab3.Facade;

import org.example.Lab3.Beverage.Beverage;

// Subsystem class used by the facade.
public class Barista {
    public void prepare(Beverage beverage) {
        System.out.println("[Barista] Preparing: " + beverage.getDescription());
    }
}
