package com.example.frutty.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashMap;
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

    @OneToOne(mappedBy = "customer")
    private Wishlist wishlist;

    @Transient
    private Map<Integer, Product> cart = new HashMap<>();

}
