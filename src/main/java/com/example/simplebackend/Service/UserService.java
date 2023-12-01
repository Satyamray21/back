package com.example.simplebackend.Service;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.simplebackend.Model.UserModel;
import com.example.simplebackend.Repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserModel getUserById(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public UserModel registerUser(UserModel userModel) {
        if (userModel == null || userModel.getLogin() == null || userModel.getPassword() == null) {
            return null;
        } else {
            userModel.setApproved(true); // Set initial approval status to true or false based on your requirement
            return userRepository.save(userModel);
        }
    }
    

    public UserModel authenticate(String login, String password) {
        return userRepository.findByLoginAndPasswordAndApprovedTrue(login, password).orElse(null);
    }

    public List<UserModel> getUnapprovedUsers() {
        return userRepository.findByApprovedFalse();
    }
    public void approveUser(Integer userId) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setApproved(true);
        userRepository.save(user);
    }
    

   
}
