package com.sda.OnlineShopMD.mapper;

import com.sda.OnlineShopMD.dto.ProductDto;
import com.sda.OnlineShopMD.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product map(ProductDto productDto){
        return Product.builder()
                .price(Integer.valueOf(productDto.getPrice())) //de unde luam price? din productdto care prinde pretul pus
                .description(productDto.getDescription())
                .name(productDto.getName())
                .category(productDto.getCategory())
                .units(Integer.valueOf(productDto.getUnits()))
                .build();
    }
}
