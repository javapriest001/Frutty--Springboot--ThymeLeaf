package com.example.frutty.Service.ServiceImpl;

import com.example.frutty.Exception.CustomerNotFoundException;
import com.example.frutty.Exception.PasswordMismatchException;
import com.example.frutty.Exception.ProductNotFoundException;
import com.example.frutty.Model.Cart;
import com.example.frutty.Model.Customer;
import com.example.frutty.Model.Product;
import com.example.frutty.Model.Wishlist;
import com.example.frutty.Repository.CustomerRepository;
import com.example.frutty.Repository.ProductRepository;
import com.example.frutty.Repository.WishlistRepository;
import com.example.frutty.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final WishlistRepository wishlistRepository;

    private Map<Integer, Product> cart = new HashMap<>();




    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ProductRepository productRepository , WishlistRepository wishlistRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.wishlistRepository = wishlistRepository;
    }




    @Override
    public Customer registerCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public boolean login(String email, String password) throws CustomerNotFoundException , PasswordMismatchException{
        boolean isLoggedIn = false;
        var customer = customerRepository.getCustomerByEmail(email).orElseThrow(()-> new CustomerNotFoundException(email));
        if (customer.getPassword().equalsIgnoreCase(password)){
            isLoggedIn = true;
        }else {
            throw new PasswordMismatchException(email);
        }
        return isLoggedIn;
    }

    @Override
    public Product findProductById(int id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException(id));
    }

    @Override
    public Map<Integer, Product> addToCart(int productId , int customerId) {
       // Map<Integer, Product> cart = new HashMap<>();
        Customer customer = getCustomerId(customerId);
        System.out.println(customer);
        Product product = findProductById(productId);
        System.out.println(product);
        if (this.cart.containsKey(product.getId())){
            Product duplicateProduct = this.cart.get(product.getId());
            duplicateProduct.setCartQuantity(duplicateProduct.getCartQuantity() + 1);
            product.setQuantityInStock(product.getQuantityInStock() - 1);
        }else{
            this.cart.put(product.getId() , product);
            product.setCartQuantity(1);
            product.setQuantityInStock(product.getQuantityInStock() - 1);
        }
        customer.setCart(this.cart);
        return customer.getCart();
    }

    @Override
    public Product getSingleProduct(int id) {
        return findProductById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    @Override
    public Wishlist addToWishList(Wishlist wishlist) {

        return wishlistRepository.save(wishlist);
    }


    public Customer getCustomerId(int customerId){
        return customerRepository.findById(customerId)
                .orElseThrow(()-> new CustomerNotFoundException(customerId));
    }

    public Customer getCustomerEmail(String email){
        return customerRepository.getCustomerByEmail(email)
                .orElseThrow(()-> new CustomerNotFoundException(email));
    }

    public int deleteWishlistByUserIdAndProductId(int customer_id , int product_id){
        return wishlistRepository.deleteWishlistByUserIdAndProductId(customer_id , product_id);
    }

}
