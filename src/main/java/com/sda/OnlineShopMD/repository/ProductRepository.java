package com.sda.OnlineShopMD.repository;

import com.sda.OnlineShopMD.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
