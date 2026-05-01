package com.hardware.pro.repository;

import com.hardware.pro.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.hardware.pro.repository.TransactionRepository;
@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {
    // Yahan hum future mein date-wise reports nikalne ke methods add kar sakte hain
}