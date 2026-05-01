package com.hardware.pro.repository;
import com.hardware.pro.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
  // AI category scanning ke liye
}