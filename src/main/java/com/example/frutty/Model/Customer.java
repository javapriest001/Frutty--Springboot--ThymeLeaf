package com.example.frutty.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false )
    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "customer",  cascade = CascadeType.ALL, orphanRemoval = true,  fetch=FetchType.LAZY)
    private List<Wishlist> wishlists;

    @Transient
    private Map<Integer, Product> cart = new HashMap<>();

}
