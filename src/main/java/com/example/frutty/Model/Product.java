package com.example.frutty.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String Description;
    private double price;
    private String category;
    private String image;
    private int quantityInStock;

    @OneToOne(mappedBy = "product")
    private Wishlist wishlist;

    @Transient
    private int cartQuantity;

}
