package com.sda.OnlineShopMD.repository;

import com.sda.OnlineShopMD.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>{

}

