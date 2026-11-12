package org.example.Lab3.Facade;

import org.example.Lab3.Beverage.Beverage;

// Subsystem class used by the facade.
public class ReceiptPrinter {
    public void print(Beverage beverage, String paymentName) {
        System.out.println("---------- RECEIPT ----------");
        System.out.println("Item:    " + beverage.getDescription());
        System.out.printf("Total:   $%.2f%n", beverage.getCost());
        System.out.println("Paid by: " + paymentName);
        System.out.println("-----------------------------");
    }
}
