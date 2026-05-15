package com.cordova.inventorymanagementsystem;

import com.cordova.inventorymanagementsystem.model.Product;
import com.cordova.inventorymanagementsystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

//@Component
public class InventoryMenu implements CommandLineRunner {

    @Autowired
    private ProductService productService;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        System.out.println("=========================================");
        System.out.println("       WELCOME TO INVENTORY SYSTEM       ");
        System.out.println("=========================================");

        while (isRunning) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Product Entry (Add New Product)");
            System.out.println("2. Inventory Tracking (Update Stock)");
            System.out.println("3. Sales Module (Sell Product / Deduct Stock)");
            System.out.println("4. Report Module (Generate Report)");
            System.out.println("5. Exit Application");
            System.out.print("Enter your choice (1-5): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        // MODULE 1: PRODUCT ENTRY
                        System.out.print("Enter product name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter starting stock quantity: ");
                        int stock = scanner.nextInt();
                        System.out.print("Enter price: ");
                        double price = scanner.nextDouble();

                        Product newProduct = new Product(name, stock, price);
                        Product saved = productService.addProduct(newProduct);
                        System.out.println("\n✅ SUCCESS: Product Added!");
                        System.out.println("   -> ID: " + saved.getId() + " | Name: " + saved.getName() + " | Stock: " + saved.getStockQuantity() + " | Price: " + saved.getPrice());
                        break;

                    case 2:
                        // MODULE 2: INVENTORY TRACKING
                        System.out.print("Enter Product ID to update: ");
                        Long updateId = scanner.nextLong();
                        System.out.print("Enter NEW total stock quantity: ");
                        int newStock = scanner.nextInt();

                        Product updated = productService.updateStock(updateId, newStock);
                        System.out.println("\n✅ SUCCESS: Stock Updated!");
                        System.out.println("   -> Product: " + updated.getName());
                        System.out.println("   -> New Total Stock: " + updated.getStockQuantity());
                        break;

                    case 3:
                        // MODULE 3: SALES MODULE
                        System.out.print("Enter Product ID to sell: ");
                        Long sellId = scanner.nextLong();
                        System.out.print("Enter quantity to sell: ");
                        int quantity = scanner.nextInt();

                        Product sold = productService.sellProduct(sellId, quantity);
                        System.out.println("\n✅ SUCCESS: Sale Completed!");
                        System.out.println("   -> Item Sold: " + sold.getName());
                        System.out.println("   -> Quantity Deducted: " + quantity);
                        System.out.println("   -> Remaining Stock: " + sold.getStockQuantity());
                        break;

                    case 4:
                        // MODULE 4: REPORT MODULE
                        productService.generateInventoryReport();
                        break;

                    case 5:
                        System.out.println("\nExiting System. Goodbye!");
                        isRunning = false;
                        break;

                    default:
                        System.out.println("\n❌ Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("\n❌ ERROR: " + e.getMessage());
                // Clear out scanner buffer in case of input mismatch
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
            }
        }
    }
}