package com.sda.OnlineShopMD.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// un produs cu cate produse vrea useru
// cartentry = o pereche de product + quantity
@Entity
@Getter
@Setter
public class CartEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Cart cart;  // o relatie "has a"
    @ManyToOne
    @JoinColumn //asa pun la relatia asta
    private Product product;
    private Integer quantity;

    @ManyToOne
    @JoinColumn
    private Order order; // si acum, un cart entry apartine de un order

}

