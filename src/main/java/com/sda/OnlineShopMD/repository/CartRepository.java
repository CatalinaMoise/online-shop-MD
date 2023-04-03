package com.sda.OnlineShopMD.repository;

import com.sda.OnlineShopMD.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.security.auth.login.AccountException;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserAccountEmail(String email);
}
