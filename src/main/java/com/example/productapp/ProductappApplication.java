package com.example.productapp;

import com.example.productapp.entities.Product;
import com.example.productapp.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductappApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductappApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository) {
        return args -> {

            productRepository.save(new Product(null, "PC Portable", 7000, 10));
            productRepository.save(new Product(null, "Smartphone", 4000, 15));
            productRepository.save(new Product(null, "Imprimante", 1200, 5));


            System.out.println("📦 Liste des produits :");
            productRepository.findAll().forEach(System.out::println);


            Product p = productRepository.findById(1L).orElse(null);
            System.out.println("🔍 Produit avec ID 1 : " + p);


            if (p != null) {
                p.setPrice(7500);
                productRepository.save(p);
                System.out.println("✏️ Produit mis à jour : " + p);
            }


            productRepository.deleteById(3L);
            System.out.println("🗑️ Produit avec ID 3 supprimé !");
        };
    }
}
