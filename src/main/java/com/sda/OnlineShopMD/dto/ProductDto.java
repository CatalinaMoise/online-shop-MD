package com.sda.OnlineShopMD.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductDto {

    private String name;
    private String description;
    private String price;
    private String category;
    private String units;

}
