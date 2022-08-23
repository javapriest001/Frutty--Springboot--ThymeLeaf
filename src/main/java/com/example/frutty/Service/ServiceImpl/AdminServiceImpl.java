package com.example.frutty.Service.ServiceImpl;

import com.example.frutty.Exception.ProductNotFoundException;
import com.example.frutty.Model.Admin;
import com.example.frutty.Model.Product;
import com.example.frutty.Repository.AdminRepository;
import com.example.frutty.Repository.ProductRepository;
import com.example.frutty.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final ProductRepository productRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository , ProductRepository productRepository) {
        this.adminRepository = adminRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Admin registerAdmin(Admin admin) {
       return adminRepository.save(admin);
    }

    @Override
    public Product getSingleProduct(int id) {
        return findProductById(id);
    }

    @Override
    public Product addNewProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Admin admin, int id) {
        Product update = findProductById(id);
        return  productRepository.save(update);
    }

    @Override
    public boolean deleteProduct(int id) {
        productRepository.deleteById(id);
        return true;
    }

    @Override
    public Product findProductById(int id) {
        return productRepository.findById(id)
               .orElseThrow(()->new ProductNotFoundException(id));
    }
    @Override
    public List<Product> getAllProducts(){
       return productRepository.findAll();
    }

}
