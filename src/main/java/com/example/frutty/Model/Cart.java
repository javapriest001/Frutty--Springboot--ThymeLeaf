package com.example.frutty.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private int userId;
    private int productQty;
    private Map<Integer , Product> cartList;
}
