package com.example.frutty.Controller;

import com.example.frutty.Exception.CustomerNotFoundException;
import com.example.frutty.Exception.PasswordMismatchException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class ExceptionController {

    @ExceptionHandler(CustomerNotFoundException.class)
    public String CustomerResourceNotFound(CustomerNotFoundException e){

        return "Customer/login";
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public String PasswordResourceNotFound(){

        return "Customer/login";
    }

}
