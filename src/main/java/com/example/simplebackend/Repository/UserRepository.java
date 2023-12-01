package com.example.simplebackend.Repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.simplebackend.Model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
    Optional<UserModel> findByLoginAndPasswordAndApprovedTrue(String login, String password);
    List<UserModel> findByApprovedFalse();
}
