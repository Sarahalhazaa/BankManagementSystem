package com.example.bankmanagementsystem.Service;

import com.example.bankmanagementsystem.Api.ApiException;
import com.example.bankmanagementsystem.Model.User;
import com.example.bankmanagementsystem.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    //ADMIN
    public List<User> getUser() {
        return authRepository.findAll();
    }
    //CUSTOMER - EMPLOYEE
    public void register(User user) {
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        authRepository.save(user);

    }

    //CUSTOMER -EMPLOYEE
    public void updateUser( Integer userId , User user) {
        User user1 = authRepository.findUserById(userId);

            user1.setPassword(user.getPassword());
            user1.setEmail(user.getEmail());
            user1.setName(user.getName());
            user1.setUserName(user.getUsername());

            authRepository.save(user1);


    }

    //ADMIN
    public void deleteUser(Integer userId) {
        User user = authRepository.findUserById(userId);
        if (user == null) {
            throw new ApiException("not found");
        }
        authRepository.delete(user);
    }





}
