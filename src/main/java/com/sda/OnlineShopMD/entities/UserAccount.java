package com.sda.OnlineShopMD.entities;

import com.sda.OnlineShopMD.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private String fullName;
    private String address;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @OneToOne( cascade = CascadeType.ALL) //daca vezi ca oriectu card nu e salvat in bd, sa il salveze
    @JoinColumn
    private Cart cart;   // si in clasa cart am specificat un userAccount, si relatia de one to one cu cart
}
