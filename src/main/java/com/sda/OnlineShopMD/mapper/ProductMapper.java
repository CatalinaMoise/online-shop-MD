package com.sda.OnlineShopMD.mapper;

import com.sda.OnlineShopMD.dto.ProductDto;
import com.sda.OnlineShopMD.entities.Product;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import org.apache.tomcat.util.codec.binary.Base64;

@Component
public class ProductMapper {
    public Product map(ProductDto productDto, MultipartFile multipartFile){
        return Product.builder()
                .price(Integer.valueOf(productDto.getPrice())) //de unde luam price? din productdto care prinde pretul pus
                .description(productDto.getDescription())
                .name(productDto.getName())
                .category(productDto.getCategory())
                .units(Integer.valueOf(productDto.getUnits()))
                .img(convertToByteArray(multipartFile))
                .build();
    }
    public ProductDto map(Product product){

        return ProductDto.builder()
                .productId(String.valueOf(product.getId()))
                .price(String.valueOf(product.getPrice()))
                .description(product.getDescription())
                .name(product.getName())
                .category(product.getCategory())
                .units(String.valueOf(product.getUnits()))
                .img(Base64.encodeBase64String(product.getImg()))
                .build();
    }
    private byte[] convertToByteArray(MultipartFile multipartFile){
        try {
            return multipartFile.getBytes();
        } catch (IOException e) {
            return new byte[0];

        }
    }
}
