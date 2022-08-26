package com.example.frutty.Controller;

import com.example.frutty.Model.Customer;
import com.example.frutty.Model.Product;
import com.example.frutty.Model.Wishlist;
import com.example.frutty.Repository.CustomerRepository;
import com.example.frutty.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Customer")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerService customerService, CustomerRepository customerRepository) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
    }

    @GetMapping(value = "/register")
    public  String registerPage(Model model){
        model.addAttribute("customer" , new Customer());
        return "Customer/register";
    }

    @PostMapping(value = "/registerUser")
    public  String registerUser(@ModelAttribute Customer customer , Model model){
        Customer registeredCustomer = customerService.registerCustomer(customer);
        if (registeredCustomer != null){

        }
        return "redirect:/Customer/register";
    }
    @GetMapping(value = "/login")
    public String loginPage(Model model){
        model.addAttribute("loginDetail" , new Customer());
        return "Customer/login";
    }

    @PostMapping(value = "/loginUser")
    public String loginUser(@ModelAttribute Customer customer , HttpSession session){
        boolean isLoggedIn = customerService.login(customer.getEmail(), customer.getPassword());
        if (!isLoggedIn){
            return "redirect:/Customer/login";
        }else {
            session.setAttribute("email" , customer.getEmail());
            return "redirect:/Customer/dashboard";
        }
    }

    @GetMapping(value = "/dashboard")
    public String dashboard(Model model , HttpSession session){
        List<Product> productList = customerService.getAllProducts();
        String email = (String) session.getAttribute("email");
        Customer customer = customerService.getCustomerEmail(email);
        model.addAttribute("products" , productList);
        model.addAttribute("id" , customer.getId());

        if(session.getAttribute("email") == null){
            return "redirect:/Customer/login";
        }else {
            return "Customer/dashboard";
        }
    }

    @PostMapping(value = "/addToCart")
    public String addToCart(Model model , HttpSession session, @RequestParam String userId , @RequestParam String productId){
        int id = Integer.parseInt(productId);
        int customerId = Integer.parseInt(userId);
      Map<Integer , Product>cart =  customerService.addToCart(id , customerId);
      model.addAttribute("cartSize", cart.size());
      session.setAttribute("cart" , cart);
      session.setAttribute("cartSize" , cart.size());
        System.out.println(session.getAttribute("cartSize"));


        System.out.println(cart);
      return "redirect:Customer/dashboard";
    }
    @PostMapping("/addToWishlist")
    public String AddToCart(@ModelAttribute Wishlist wishlist){
        customerService.addToWishList(wishlist);
        return "redirect:/Customer/dashboard";
    }



}
