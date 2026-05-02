package com.hardware.pro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.hardware.pro.model.Product;
import com.hardware.pro.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Controller // '@RestController' ko hata diya hai conflict khatam karne ke liye
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    // 1. DASHBOARD (Direct localhost:8080/ par chalega)
    @GetMapping({"/", "/dashboard"})
    public String index(Model model) {
        // Aapka pichla working logic
        return "dashboard";
    }
    @GetMapping("/add-stock")
    public String addproduct() {
        // Aapka pichla working logic
        return "add-stock";
    }
    @GetMapping("/add-vendor")
    public String addvendor() {
        // Aapka pichla working logic
        return "add-vendor";
    }
    @GetMapping("/return")
    public String returnn() {
        // Aapka pichla working logic
        return "return";
    }
    @GetMapping("/scanner")
    public String scanner() {
        // Aapka pichla working logic
        return "scanner";
    }
    // 2. ALL PRODUCTS (Path: /api/inventory/products)
    @GetMapping("/api/inventory/products")
    @ResponseBody
    public List<Product> getAll() {
        return inventoryService.getAllProducts();
    }

    // 3. UPDATE STOCK (Path: /api/inventory/update-stock)
    @PostMapping("/api/inventory/update-stock")
    @ResponseBody
    public String updateStock(@RequestBody Map<String, Object> payload) {
        String id = (String) payload.get("id");
        int qty = Integer.parseInt(payload.get("newQty").toString());
        double pPrice = Double.parseDouble(payload.get("newPurchasePrice").toString());
        double sPrice = Double.parseDouble(payload.get("newSellingPrice").toString());

        int limit = payload.containsKey("lowStockLimit") ?
                Integer.parseInt(payload.get("lowStockLimit").toString()) : 0;

        inventoryService.updateStockOnPurchase(id, qty, pPrice, sPrice, limit);
        return "Stock Updated Successfully with Limit: " + limit;
    }

    // 4. RETURN PROCESS (Path: /api/inventory/return)
    @PostMapping("/api/inventory/return")
    @ResponseBody
    public String processReturn(@RequestBody Map<String, Object> payload) {
        String id = (String) payload.get("id");
        int qty = Integer.parseInt(payload.get("qty").toString());
        double refund = Double.parseDouble(payload.get("refundAmount").toString());

        inventoryService.handleReturn(id, qty, refund);
        return "Return Processed and Stock Restored!";
    }
}