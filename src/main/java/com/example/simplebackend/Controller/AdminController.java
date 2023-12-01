package com.example.simplebackend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.simplebackend.Model.UserModel;
import com.example.simplebackend.Service.UserService;

import java.util.List;

@Controller
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin-login")
    public String adminLoginPage() {
        return "admin-login";
    }

    @PostMapping("/admin-login")
    public String adminLogin(@RequestParam String username, @RequestParam String password, Model model) {
        if ("admin".equals(username) && "1234".equals(password)) {
            return "redirect:/admin/unapproved-users";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "admin-login";
        }
    }

    @GetMapping("/admin/unapproved-users")
    public String getUnapprovedUsers(Model model) {
        List<UserModel> unapprovedUsers = userService.getUnapprovedUsers();
        model.addAttribute("unapprovedUsers", unapprovedUsers);
        return "admin-dashboard";
    }

    @GetMapping("/view-details/{userId}")
    public String viewDetails(@PathVariable Integer userId, Model model) {
        UserModel userDetails = userService.getUserById(userId);
        model.addAttribute("userDetails", userDetails);
        return "view-details";
    }

    @GetMapping("/admin/approve-user/{userId}")
    public String getApproveUserPage(@PathVariable Integer userId, Model model) {
        UserModel user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "approve-user";
    }

    @PostMapping("/admin/approve-user/{userId}")
    public String approveUser(@PathVariable Integer userId, Model model) {
        userService.approveUser(userId);
        return "redirect:/admin/unapproved-users";
    }
}
