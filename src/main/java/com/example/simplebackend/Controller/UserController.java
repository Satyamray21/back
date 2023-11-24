package com.example.simplebackend.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/register")
    public String getRegisterPage()
    {
        return "register_Page";
    }
    @GetMapping("/login")
    
        public String getLoginPage()
        {
            return "login_Page";
        }
    }

