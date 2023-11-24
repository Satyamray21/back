package com.example.simplebackend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.simplebackend.Model.UserModel;
import com.example.simplebackend.Service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerRequest", new UserModel());
        return "register_Page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginRequest", new UserModel());
        return "login_Page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserModel userModel) {
        System.out.println("register request: " + userModel);
        UserModel registeredUser = userService.registerUser(userModel.getLogin(), userModel.getPassword(), userModel.getEmail());
        // You should add some logic here for handling the registered user
        return registeredUser == null ? "error_Page" : "redirect:/login"; 
    }
    @PostMapping("/login")
    public String login(@ModelAttribute UserModel userModel,Model model) {
        System.out.println("login request: " + userModel);
        UserModel authenticated= userService.authenticate(userModel.getLogin(),userModel.getPassword());
        if(authenticated !=null){
            model.addAttribute("userLogin",authenticated.getLogin());
            return "personal_Page";
        }else{
            return "error_Page";
        }
    }
}
