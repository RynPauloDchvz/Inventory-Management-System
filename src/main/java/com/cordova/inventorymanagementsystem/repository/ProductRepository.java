package com.cordova.inventorymanagementsystem.repository;

import com.cordova.inventorymanagementsystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Dahil nag-extend tayo sa JpaRepository, mayroon na tayong built-in functions
    // tulad ng save(), findAll(), findById(), at deleteById() nang libre!
}