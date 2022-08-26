package com.example.frutty.Service;

import com.example.frutty.Model.Admin;
import com.example.frutty.Model.Product;

import java.util.List;

public interface AdminService {
    Admin registerAdmin(Admin admin);
    Product getSingleProduct(int id);
    Product addNewProduct(Product product);
    Product updateProduct(Product product);
    boolean deleteProduct(int id);
    Admin getCustomerEmail(String email);
    boolean loginAdmin(String email, String password);
    Product findProductById(int id);

    List<Product> getAllProducts();
}
