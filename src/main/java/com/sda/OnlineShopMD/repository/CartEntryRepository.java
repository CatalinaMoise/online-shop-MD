package com.sda.OnlineShopMD.repository;

import com.sda.OnlineShopMD.entities.CartEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartEntryRepository extends JpaRepository<CartEntry, Long> {
}
