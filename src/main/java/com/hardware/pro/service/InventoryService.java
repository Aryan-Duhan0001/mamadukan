package com.hardware.pro.service;

import com.hardware.pro.model.Product;
import com.hardware.pro.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.hardware.pro.model.Transaction;
@Service
public class InventoryService {

    @Autowired
    private ProductRepository productRepo;

    // Flowchart Box: "Update Stock"
    public void updateStockOnPurchase(String id, int newQty, double newPurchasePrice, double newSellingPrice, int lowStockLimit) {
        // Mirror Protocol: Find and update or create new
        Product p = productRepo.findById(id).orElse(new Product());
        p.setId(id);

        // Logical Step: Purane stock mein naya quantity jodna
        p.setCurrentStock(p.getCurrentStock() + newQty);
        p.setPurchasePrice(newPurchasePrice);
        p.setSellingPrice(newSellingPrice);
        p.setLowStockLimit(lowStockLimit);

        productRepo.save(p);
    }

    // Flowchart Box: "Get Inventory List"
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }
    // InventoryService.java ke andar ye method add karein:

    public void handleReturn(String id, int qty, double refundAmount) {
        // 1. Product dhoondo (Mirror Protocol: Error throw karega agar nahi mila)
        Product p = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // 2. Logic Mirror: Stock wapas dukan mein aaya (+ qty)
        p.setCurrentStock(p.getCurrentStock() + qty);
        productRepo.save(p);

        // 3. Transaction Entry: Return ka record banana
        Transaction t = new Transaction();
        t.setProductId(id);
        t.setProductName(p.getProductName());
        t.setQuantity(-qty); // Negative dikhayega taaki pata chale return hai
        t.setTotalAmount(-refundAmount); // Gulle se paise kam huye

        // Profit logic: (Selling - Purchase) * Qty ko negative mein save karna
        double profitPerUnit = p.getSellingPrice() - p.getPurchasePrice();
        t.setNetProfit(-(profitPerUnit * qty));

        t.setDate(new java.util.Date());
        t.setType("RETURN");

        // Note: Iske liye TransactionRepository aur Model hona zaruri hai
        // transRepo.save(t);
    }
    // Logic: Low Stock Check (Mirroring the decision box)
    public boolean isLowStock(Product p) {
        return p.getCurrentStock() <= p.getLowStockLimit();
    }
}