package com.example.frutty.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="customers")
public class Customer {

    @Id
    private int id;
    private String name;
    private String email;
    private String password;

    @Transient
    private Map<Integer, Product> cart;

}
