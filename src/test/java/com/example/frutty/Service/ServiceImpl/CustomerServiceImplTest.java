package com.example.frutty.Service.ServiceImpl;

import com.example.frutty.Exception.CustomerNotFoundException;
import com.example.frutty.Model.Customer;
import com.example.frutty.Model.Product;
import com.example.frutty.Model.Wishlist;
import com.example.frutty.Repository.CustomerRepository;
import com.example.frutty.Repository.ProductRepository;
import com.example.frutty.Repository.WishlistRepository;
import com.example.frutty.Service.ProductService;
import com.example.frutty.Service.WishlistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;
    @Mock
    ProductRepository productRepository;
    @Mock
    WishlistRepository wishlistRepository;

    @InjectMocks
    CustomerServiceImpl customerService;



    private Customer customer;
    private Product product;
    private Product product2;
    private Wishlist wishlist;


    @BeforeEach
    void setUp() {
        Map<Integer, Product> cart = new HashMap<>();
         List<Wishlist> wishlists = new ArrayList<>();

        customer = new Customer(1, "vincent" , "enwerevincent@gmail.com", "Fbiswats1", wishlists, cart);
        product = new Product(1, "Pineapple" , "Tasty and fruity" , 9.7, "Round" , "0xEdfccee" , 12, wishlists , 0);
        product2 = new Product(2, "apple" , "Tasty and fruity" , 9.7, "Round" , "0xEdfccee" , 12 , wishlists, 0);
        wishlist = new Wishlist(1 , customer, product);
        List<Product> productList = new ArrayList<>(Arrays.asList(product , product2));
        when(wishlistRepository.save(wishlist)).thenReturn(wishlist);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerRepository.findById(1)).thenReturn(Optional.ofNullable(customer));
        when(productRepository.findById(1)).thenReturn(Optional.ofNullable(product));
        when(customerRepository.getCustomerByEmail("enwerevincent@gmail.com")).thenReturn(Optional.ofNullable(customer));
        when(productRepository.findAll()).thenReturn(productList);
    }

    @Test
    void registerCustomer() {
        assertEquals(customer , customerService.registerCustomer(customer));
    }

    @Test
    void login_Success() {
        assertTrue(customerService.login("enwerevincent@gmail.com" , "Fbiswats1"));
    }

    @Test
    void findProductById() {
        assertEquals(product , customerService.findProductById(1));
    }

    @Test
    void addToCart() {
        Map<Integer, Product> cart = new HashMap<>();
        assertEquals(1 , customerService.addToCart(product.getId() , customer.getId()).size());

    }

    @Test
    void getSingleProduct() {
        assertEquals(product, customerService.findProductById(1));
    }

    @Test
    void getAllProducts() {
        assertEquals(2 , customerService.getAllProducts().size());
    }

    @Test
    void addToWishList() {
        assertEquals(wishlist , customerService.addToWishList(wishlist));
    }

    @Test
    void getCustomerId() {
        assertEquals(customer , customerService.getCustomerId(1));
    }
}