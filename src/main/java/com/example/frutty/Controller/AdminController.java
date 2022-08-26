package com.example.frutty.Controller;

import com.example.frutty.Model.Admin;
import com.example.frutty.Model.Customer;
import com.example.frutty.Model.Product;
import com.example.frutty.Repository.AdminRepository;
import com.example.frutty.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/admin" )
public class AdminController {

    private final AdminRepository adminRepository;
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminRepository adminRepository, AdminService adminService) {
        this.adminRepository = adminRepository;
        this.adminService = adminService;
    }


    @GetMapping( value="/register")
    public String registerPage(Model model){
        model.addAttribute("admin" , new Admin());
        return "Admin/register";
    }


    @PostMapping(value="/registerAdmin")
    public String registerAdmin(@ModelAttribute Admin admin ){
        adminService.registerAdmin(admin);
        return "redirect:/admin/loginAdmin";
    }

    @GetMapping( value="/login")
    public String loginPage(Model model){
        model.addAttribute("loggedInAdmin" , new Admin());
        return "Admin/login";
    }

    @PostMapping( value="/loginAdmin")
    public String loginAdmin(@ModelAttribute Admin admin, Model model, HttpSession session){
        boolean isLoggedIn = adminService.loginAdmin(admin.getEmail() , admin.getPassword());
        if (!isLoggedIn){
            return "redirect:/admin/login";
        }else {
            session.setAttribute("email" , admin.getEmail());
            return "redirect:/admin/dashboard";
        }
    }

    @GetMapping( value="/dashboard")
    public String dashboard(Model model , HttpSession session){
        List<Product> productList = adminService.getAllProducts();
        String email = (String) session.getAttribute("email");
        Admin admin = adminService.getCustomerEmail(email);
        model.addAttribute("admin" , new Admin());
        model.addAttribute("products" , productList);
        model.addAttribute("id" , admin.getId());
        model.addAttribute("product" , new Product());

        if(session.getAttribute("email") == null){
            return "redirect:/admin/loginAdmin";
        }else {
            return "Admin/dashboard";
        }
    }

    @GetMapping(value = "/getProduct/{productId}")
    public String getProduct(@PathVariable(name="productId") String productId , Model model){
        int id = Integer.parseInt(productId);
        System.out.println(adminService.findProductById(id));
        model.addAttribute("product" , adminService.findProductById(id));
        return "Admin/product";
    }

    @GetMapping(value = "/editProduct/{productId}")
    public String editProduct(@PathVariable(name="productId") String productId , Model model){
        int id = Integer.parseInt(productId);
       // System.out.println(adminService.findProductById(id));
        model.addAttribute("product" , adminService.findProductById(id));
        model.addAttribute("productField" , new Product());
        return "Admin/editProduct";
    }

    @PostMapping(value = "/editProduct")
    public String adminEditProduct(@ModelAttribute Product product , Model model){
        System.out.println(product);
       adminService.updateProduct(product);
        return "redirect:/admin/dashboard";
    }

    @PostMapping(value = "/deleteProduct/{id}")
    public String adminDeleteProduct(@PathVariable(name = "id") String id , Model model){
        int productId = Integer.parseInt(id);
        adminService.deleteProduct(productId);
        return "redirect:/admin/dashboard";
    }



    @PostMapping(value = "/addProduct")
    public String addProduct(@ModelAttribute Product product){
        adminService.addNewProduct(product);
        return "redirect:/admin/dashboard";
    }






}
