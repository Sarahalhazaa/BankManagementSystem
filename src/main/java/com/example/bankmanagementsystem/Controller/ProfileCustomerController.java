package com.example.bankmanagementsystem.Controller;

import com.example.bankmanagementsystem.Api.ApiResponse;
import com.example.bankmanagementsystem.DTO.CustomerDTO;
import com.example.bankmanagementsystem.Model.Customer;
import com.example.bankmanagementsystem.Service.ProfileCustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profileCustomer")
@RequiredArgsConstructor
public class ProfileCustomerController {
    Logger logger = LoggerFactory.getLogger(ProfileCustomerService.class);
    private final ProfileCustomerService profileCustomerService;

    //ADMIN
    @GetMapping("/get")
    public ResponseEntity getProfileCustomers(){
        logger.info("get Profile Customers");
        return ResponseEntity.status(200).body(profileCustomerService.getProfileCustomer());
    }

    //CUSTOMER
    @PostMapping("/register")
    public ResponseEntity addProfileCustomer(@AuthenticationPrincipal Customer customer, @RequestBody @Valid CustomerDTO customerDTO){
        logger.info("add Profile Customer");
        profileCustomerService.addProfile(customer.getId(), customerDTO);
        return ResponseEntity.ok().body("Profile Customer added successfully");
    }
    //CUSTOMER
    @PutMapping("/Update")
    public ResponseEntity UpdateProfileCustomer(@AuthenticationPrincipal Customer customer, @RequestBody @Valid CustomerDTO customerDTO){
        logger.info("Update Profile Customer");
        profileCustomerService.updateProfile(customer.getId(), customerDTO);
        return ResponseEntity.ok().body(new ApiResponse("Profile Customer Update"));

    }
    //CUSTOMER
    @DeleteMapping("/delete")
    public ResponseEntity deleteProfileCustomer( @AuthenticationPrincipal Customer customer){
        logger.info("delete Profile Customer");
        profileCustomerService.deleteProfile(customer.getId());
        return ResponseEntity.ok().body(new ApiResponse("Profile Customer Deleted"));

    }
}
