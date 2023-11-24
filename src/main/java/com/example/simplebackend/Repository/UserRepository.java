package com.example.simplebackend.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.simplebackend.Model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Integer > {
          Optional<UserModel> findByLoginAndPassword(String login,String password);
 
}
