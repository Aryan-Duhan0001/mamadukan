package com.hardware.pro.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "vendors")
public class Vendor {
    @Id
    private String id;
    private String vendorName;       // Dukandar ka asali naam
    private String contactNumber;    // WhatsApp order ke liye
    private String categoryProvided; // Kis tarah ka maal dete hain
}