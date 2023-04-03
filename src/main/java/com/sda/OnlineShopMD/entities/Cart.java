package com.sda.OnlineShopMD.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //aici punem relatia de one to one, la cart si useraccount - un card de account, un account de cart
    @OneToOne(mappedBy = "cart")
    private UserAccount userAccount;   // relatie de compozitie - has a -
    @OneToMany(mappedBy = "cart")  //"cart" e variabila ce face referire la legatura"
    private List<CartEntry> cartEntryList;
}
