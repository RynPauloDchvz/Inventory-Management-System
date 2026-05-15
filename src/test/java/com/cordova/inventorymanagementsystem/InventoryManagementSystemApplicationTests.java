package com.cordova.inventorymanagementsystem;

import com.cordova.inventorymanagementsystem.model.Product;
import com.cordova.inventorymanagementsystem.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InventoryManagementSystemApplicationTests {

	@Autowired
	private ProductService productService;

	@Test
	void verifyIntegrationRequirements() {
		// 1. PRODUCT ENTRY: Mag-add ng bagong product (Halimbawa: 50 Laptops)
		Product newProduct = new Product("Laptop", 50, 25000.0);
		Product savedProduct = productService.addProduct(newProduct);

		System.out.println("✅ Added 50 Laptops to inventory.");

		// 2. SALES MODULE: Mag-benta ng 45 na laptops
		// Expected: Mababawasan ang stock at magti-trigger ang low-stock alert (5 na lang ang matitira)
		System.out.println("🛒 Selling 45 Laptops...");
		Product updatedProduct = productService.sellProduct(savedProduct.getId(), 45);

		// VERIFY 1: Sales reduce stock
		assertEquals(5, updatedProduct.getStockQuantity(), "Stock should be reduced to 5");
		System.out.println("✅ Verified: Sales reduced stock to " + updatedProduct.getStockQuantity());

		// 3. REPORT MODULE: Generate report
		List<Product> report = productService.generateInventoryReport();

		// VERIFY 2: Reports reflect updated inventory
		assertEquals(5, report.get(0).getStockQuantity(), "Report should show 5 laptops remaining");
		System.out.println("✅ Verified: Report reflects the updated inventory.");

		// Note: Yung Low-stock alert ay lilitaw din sa console dahil tinawag natin yung sellProduct
	}
}