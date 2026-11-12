package org.example.Lab3;

import org.example.Lab3.Beverage.Beverage;
import org.example.Lab3.Beverage.Espresso;
import org.example.Lab3.Beverage.Tea;
import org.example.Lab3.Decorator.ExtraShot;
import org.example.Lab3.Decorator.Milk;
import org.example.Lab3.Decorator.Syrup;
import org.example.Lab3.Facade.Barista;
import org.example.Lab3.Facade.CoffeeShopFacade;
import org.example.Lab3.Facade.ReceiptPrinter;
import org.example.Lab3.Factory.CardPaymentFactory;
import org.example.Lab3.Factory.CashPaymentFactory;
import org.example.Lab3.Factory.PaymentFactory;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CoffeeShopFacade shop = new CoffeeShopFacade(new Barista(), new ReceiptPrinter());
        // to add a new payment type, write its factory and add it to this list
        List<PaymentFactory> payments = List.of(new CardPaymentFactory(), new CashPaymentFactory());

        System.out.println("=== Welcome to the Coffee Shop ===");

        // 1. Choose the base drink
        System.out.println("Choose a drink: 1) Espresso ($2.00)  2) Tea ($1.50)");
        Beverage drink = readInt(scanner) == 2 ? new Tea() : new Espresso();

        // 2. Wrap it with add-ons (Decorator) as many times as the user wants
        while (true) {
            System.out.printf("Current: %s = $%.2f%n", drink.getDescription(), drink.getCost());
            System.out.println("Add: 1) Milk (+0.50)  2) Syrup (+0.60)  3) Extra Shot (+0.80)  0) Done");
            int choice = readInt(scanner);
            if (choice == 0) {
                break;
            }
            drink = switch (choice) {
                case 1 -> new Milk(drink);
                case 2 -> new Syrup(drink);
                case 3 -> new ExtraShot(drink);
                default -> drink;
            };
        }

        // 3. Choose the payment factory (Factory Method)
        System.out.print("Pay with:");
        for (int i = 0; i < payments.size(); i++) {
            System.out.print("  " + (i + 1) + ") " + payments.get(i).getName());
        }
        System.out.println();
        int index = Math.min(Math.max(readInt(scanner), 1), payments.size()) - 1;

        // 4. One call to the Facade does everything else (it goes through the Proxy)
        shop.placeOrder(drink, payments.get(index));
    }

    private static int readInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Please enter a number.");
        }
        return scanner.nextInt();
    }
}
