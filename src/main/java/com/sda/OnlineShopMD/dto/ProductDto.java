package com.sda.OnlineShopMD.dto;

import lombok.*;

@Data //getter si setter
@Builder //fiecare builder -> contructor
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String productId; //am adaugat id, pentru a avea un url cu id pentru fiecare produs

    private String name;
    private String description;
    private String price;
    private String category;
    private String units;
    @ToString.Exclude
    private String img;


}
