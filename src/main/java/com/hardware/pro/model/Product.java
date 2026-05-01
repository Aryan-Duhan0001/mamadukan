package com.hardware.pro.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Product {
    @Id
    private String id;
    private String productName;
    private int currentStock;
    private double purchasePrice;
    private double sellingPrice;
    private int lowStockLimit; // Wo box jo humne flowchart mein socha tha

    // --- GETTERS & SETTERS (Standard Mirroring) ---
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public int getCurrentStock() { return currentStock; }
    public void setCurrentStock(int currentStock) { this.currentStock = currentStock; }

    public double getPurchasePrice() { return purchasePrice; }
    public void setPurchasePrice(double purchasePrice) { this.purchasePrice = purchasePrice; }

    public double getSellingPrice() { return sellingPrice; }
    public void setSellingPrice(double sellingPrice) { this.sellingPrice = sellingPrice; }

    public int getLowStockLimit() { return lowStockLimit; }
    public void setLowStockLimit(int lowStockLimit) { this.lowStockLimit = lowStockLimit; }

    // Logic Mirror: Profit calculation
    public double getProfitPerUnit() {
        return this.sellingPrice - this.purchasePrice;
    }
}