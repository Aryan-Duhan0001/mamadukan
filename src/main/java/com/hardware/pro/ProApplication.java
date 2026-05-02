package com.hardware.pro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.hardware.pro.repository")
public class ProApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProApplication.class, args);

        System.out.println("========================================");
        System.out.println("🚀 HARDWARE PRO V2 IS LIVE!");
        System.out.println("Dukan ka kaam shuru karne ke liye taiyar!");
        System.out.println("========================================");
    }
}