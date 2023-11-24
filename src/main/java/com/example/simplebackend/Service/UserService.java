package com.example.simplebackend.Service;

import org.springframework.stereotype.Service;

import com.example.simplebackend.Model.UserModel;
import com.example.simplebackend.Repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

   
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel registerUser(String login, String password, String email) {
        if (login == null || password == null) {
            return null;
        } else {
            UserModel userModel = new UserModel();
            userModel.setLogin(login);
            userModel.setPassword(password);
            userModel.setEmail(email);
            return userRepository.save(userModel);
        }
    }

    public UserModel authenticate(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password).orElse(null);
    }
}
