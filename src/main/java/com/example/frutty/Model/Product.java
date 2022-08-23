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
    private int id;
    private String name;
    private String Description;
    private double price;
    private String category;
    private String image;
    private int quantityInStock;

    @Transient
    private int cartQuantity;

}
