package com.cordova.inventorymanagementsystem.service;

import com.cordova.inventorymanagementsystem.model.Product;
import com.cordova.inventorymanagementsystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // MODULE 1: Product Entry (Add products)
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // MODULE 2: Inventory Tracking (Update stocks)
    public Product updateStock(Long productId, int newQuantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found."));

        product.setStockQuantity(newQuantity);
        return productRepository.save(product);
    }

    // MODULE 3: Sales Module (Deduct inventory & Low-stock alerts)
    public Product sellProduct(Long productId, int quantitySold) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found."));

        if (product.getStockQuantity() < quantitySold) {
            throw new RuntimeException("Insufficient stock for this transaction.");
        }

        // Deduct inventory
        product.setStockQuantity(product.getStockQuantity() - quantitySold);
        Product updatedProduct = productRepository.save(product);

        // Low-stock alert (Triggers if stock is 10 or below)
        if (updatedProduct.getStockQuantity() <= 10) {
            System.out.println("⚠️ ALERT: Low stock for " + updatedProduct.getName() + "! Only " + updatedProduct.getStockQuantity() + " remaining.");
        }

        return updatedProduct;
    }

    // MODULE 4: Report Module (Generate reports)
    public List<Product> generateInventoryReport() {
        List<Product> allProducts = productRepository.findAll();

        System.out.println("\n--- INVENTORY REPORT ---");
        for (Product p : allProducts) {
            System.out.println("ID: " + p.getId() + " | Product: " + p.getName() + " | Stock: " + p.getStockQuantity() + " | Price: " + p.getPrice());
        }
        System.out.println("------------------------\n");

        return allProducts;
    }
}