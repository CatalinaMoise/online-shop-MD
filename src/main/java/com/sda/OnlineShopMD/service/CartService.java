package com.sda.OnlineShopMD.service;

import com.sda.OnlineShopMD.dto.ProductQuantityDto;
import com.sda.OnlineShopMD.entities.Cart;
import com.sda.OnlineShopMD.entities.CartEntry;
import com.sda.OnlineShopMD.entities.Product;
import com.sda.OnlineShopMD.repository.CartEntryRepository;
import com.sda.OnlineShopMD.repository.CartRepository;
import com.sda.OnlineShopMD.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class CartService {
    @Autowired  //injectam, ca sa putem sa il apelam mai jos
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartEntryRepository cartEntryRepository;
    public void addToCart(String productId, ProductQuantityDto productQuantityDto, String loggedInUserEmail){
        CartEntry cartEntry = new CartEntry();
        Cart cart = cartRepository.findByUserAccountEmail(loggedInUserEmail);
        cartEntry.setCart(cart);
        Optional<Product> optionalProduct = productRepository.findById(Long.valueOf(productId));
        if(optionalProduct.isEmpty()){
            throw new RuntimeException("product is not valid");
        }
        Product product = optionalProduct.get();
        cartEntry.setProduct(product);
        cartEntry.setQuantity(Integer.valueOf(productQuantityDto.getQuantity()));

        cartEntryRepository.save(cartEntry);


    } // cum ne asiguram ca orice user are un card
}
