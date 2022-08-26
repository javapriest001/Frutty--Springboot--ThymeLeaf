package com.example.frutty.Dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class AdminDTO {

    private String name;

    private String email;

    private String password;

}
