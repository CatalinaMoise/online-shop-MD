package com.sda.OnlineShopMD.service;

import com.sda.OnlineShopMD.dto.ProductDto;
import com.sda.OnlineShopMD.entities.Product;
import com.sda.OnlineShopMD.mapper.ProductMapper;
import com.sda.OnlineShopMD.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;
    public void create(ProductDto productDto){
        Product product = productMapper.map(productDto); //echivalent operatiunii int x = 1+2
        productRepository.save(product); //product va intra in baza de date

    }
}
