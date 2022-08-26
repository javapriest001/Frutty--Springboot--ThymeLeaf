package com.example.frutty.Service.ServiceImpl;

import com.example.frutty.Exception.CustomerNotFoundException;
import com.example.frutty.Exception.PasswordMismatchException;
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
    public Product updateProduct(Product product) {
        return  productRepository.save(product);
    }

    @Override
    public boolean deleteProduct(int id) {
        productRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean loginAdmin(String email, String password) throws CustomerNotFoundException, PasswordMismatchException {
        boolean isLoggedIn = false;
        var admin = adminRepository.getAdminByEmail(email).orElseThrow(()-> new CustomerNotFoundException(email));
        if (admin.getPassword().equalsIgnoreCase(password)){
            isLoggedIn = true;
        }else {
            throw new PasswordMismatchException(email);
        }
        return isLoggedIn;
    }

    public Admin getCustomerEmail(String email){
        return adminRepository.getAdminByEmail(email)
                .orElseThrow(()-> new CustomerNotFoundException(email));
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
