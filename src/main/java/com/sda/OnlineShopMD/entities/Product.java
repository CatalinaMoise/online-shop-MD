package com.sda.OnlineShopMD.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    private String description;
    @NonNull
    private Integer price;
    @NonNull
    private String category;
    @NonNull
    private Integer units;
    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] img;
    @OneToMany(mappedBy = "product")
    private List<CartEntry> cartEntryList;

}
