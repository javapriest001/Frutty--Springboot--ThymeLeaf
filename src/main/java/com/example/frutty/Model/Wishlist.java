package com.example.frutty.Model;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Wishlists")
public class Wishlist {
    @Id
    private int id;
    private int user_id;
    private int product_id;
}
