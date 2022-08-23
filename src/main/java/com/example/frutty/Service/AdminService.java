package com.example.frutty.Service;

import com.example.frutty.Model.Admin;
import com.example.frutty.Model.Product;

import java.util.List;

public interface AdminService {
    Admin registerAdmin(Admin admin);
    Product getSingleProduct(int id);
    Product addNewProduct(Product product);
    Product updateProduct(Admin admin , int id);
    boolean deleteProduct(int id);

    Product findProductById(int id);

    List<Product> getAllProducts();
}
