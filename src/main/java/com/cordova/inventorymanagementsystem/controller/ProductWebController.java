package com.cordova.inventorymanagementsystem.controller;

import com.cordova.inventorymanagementsystem.model.Product;
import com.cordova.inventorymanagementsystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductWebController {

    @Autowired
    private ProductService productService;

    // MODULE 4: REPORT MODULE
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listProducts", productService.generateInventoryReport());
        model.addAttribute("newProduct", new Product());
        return "index";
    }

    // MODULE 1: PRODUCT ENTRY
    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute("newProduct") Product product) {
        productService.addProduct(product);
        return "redirect:/";
    }

    // MODULE 2: INVENTORY TRACKING (Update Total Stock)
    @PostMapping("/updateStock")
    public String updateStock(@RequestParam("productId") Long productId, @RequestParam("newQuantity") int newQuantity) {
        productService.updateStock(productId, newQuantity);
        return "redirect:/";
    }

    // MODULE 3: SALES MODULE (Deduct Stock)
    @PostMapping("/sellProduct")
    public String sellProduct(@RequestParam("productId") Long productId, @RequestParam("quantity") int quantity) {
        try {
            productService.sellProduct(productId, quantity);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return "redirect:/";
    }
}