package com.sda.OnlineShopMD.service;

import com.sda.OnlineShopMD.dto.CartEntryDto;
import com.sda.OnlineShopMD.dto.CheckoutDto;
import com.sda.OnlineShopMD.dto.ProductQuantityDto;
import com.sda.OnlineShopMD.entities.Cart;
import com.sda.OnlineShopMD.entities.CartEntry;
import com.sda.OnlineShopMD.entities.Product;
import com.sda.OnlineShopMD.mapper.CartEntryMapper;
import com.sda.OnlineShopMD.repository.CartEntryRepository;
import com.sda.OnlineShopMD.repository.CartRepository;
import com.sda.OnlineShopMD.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired  //injectam, ca sa putem sa il apelam mai jos
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartEntryRepository cartEntryRepository;
    @Autowired
    private CartEntryMapper cartEntryMapper;
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

    public CheckoutDto getCheckoutDtoByUserEmail(String userEmail) {
        Cart cart = cartRepository.findByUserAccountEmail(userEmail);
        List<CartEntryDto> cartEntryDtoList = new ArrayList<>();
        Integer subtotal = 0;
        for(CartEntry cartEntry: cart.getCartEntryList()){
            CartEntryDto cartEntryDto=cartEntryMapper.map(cartEntry);
            cartEntryDtoList.add(cartEntryDto);
            subtotal = subtotal+cartEntry.getProduct().getPrice()*cartEntry.getQuantity();


        }
        return CheckoutDto.builder()
                .cartEntryDtoList(cartEntryDtoList)
                .subtotal(String.valueOf(subtotal))
                .shippingFee("50")
                .total(String.valueOf(subtotal+50))
                .build();
    }
}
