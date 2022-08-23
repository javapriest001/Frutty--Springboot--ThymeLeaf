package com.example.frutty.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name" , nullable = false)
    private String name;
    @Column(name = "email" , nullable = false)
    private String email;
    @Column(name = "password" , nullable = false)
    private String password;

}
