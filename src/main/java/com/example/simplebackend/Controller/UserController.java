package com.example.simplebackend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.simplebackend.Model.UserModel;
import com.example.simplebackend.Service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

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
    public String register(@ModelAttribute UserModel userModel, Model model) {
        System.out.println("register request: " + userModel);
        userModel.setApproved(false); 
    UserModel registeredUser = userService.registerUser(userModel);
        if (registeredUser != null) {
            model.addAttribute("message", "Registration request sent. Waiting for admin approval.");
            return "registration-success";
        } else {
            return "error_Page";
        }
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserModel userModel, Model model) {
        System.out.println("login request: " + userModel);
        UserModel authenticated = userService.authenticate(userModel.getLogin(), userModel.getPassword());
        
        if (authenticated != null && authenticated.isApproved()) {
            model.addAttribute("userLogin", authenticated.getLogin());
            return "personal_Page";
        } else {
            return "error_Page";
        }
    }
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        // Perform logout actions, such as clearing the session
        session.invalidate();
        return "redirect:/login"; // Redirect to the login page after logout
    }
}

