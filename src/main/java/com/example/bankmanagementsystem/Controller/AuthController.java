package com.example.bankmanagementsystem.Controller;

import com.example.bankmanagementsystem.Api.ApiResponse;
import com.example.bankmanagementsystem.Model.User;
import com.example.bankmanagementsystem.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    //ADMIN
    @GetMapping("/get")
    public ResponseEntity getusers(){
        logger.info("get users");
        return ResponseEntity.status(200).body(authService.getUser());
    }

    //All
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User user){
        logger.info("register user");
        authService.register(user);
        return ResponseEntity.ok().body("user added successfully");
    }
//CUSTOMER - EMPLOYEE
    @PutMapping("/Update")
    public ResponseEntity UpdateUser(@AuthenticationPrincipal User user1, @RequestBody @Valid User user){
        logger.info("update user");
        authService.updateUser(user1.getId(), user);
        return ResponseEntity.ok().body(new ApiResponse("User Update"));

    }
//ADMIN
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity deleteUser( @PathVariable Integer userId){
        logger.info("delete user");
        authService.deleteUser(userId);
        return ResponseEntity.ok().body(new ApiResponse("User Deleted"));

    }


    @PostMapping("/login")
    public ResponseEntity  login( String username, String password){
        logger.info("login user");
        return ResponseEntity.ok().body(" ");
    }

    @PostMapping("/logOut")
    public ResponseEntity  logOut(){
        logger.info("logout user");
        return ResponseEntity.ok().body(" ");
    }
}
