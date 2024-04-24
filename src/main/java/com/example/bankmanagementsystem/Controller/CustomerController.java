package com.example.bankmanagementsystem.Controller;

import com.example.bankmanagementsystem.Api.ApiResponse;
import com.example.bankmanagementsystem.Model.Customer;
import com.example.bankmanagementsystem.Model.User;
import com.example.bankmanagementsystem.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    Logger logger = LoggerFactory.getLogger(CustomerController.class);
private final CustomerService customerService;

    //ADMIN
    @GetMapping("/get")
    public ResponseEntity getCustomer(){
        logger.info("get users");
        return ResponseEntity.status(200).body(customerService.getCustomer());
    }

    //CUSTOMER
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User user){
        logger.info("register Customer");
        customerService.register(user);
        return ResponseEntity.ok().body("Customer added successfully");
    }
    //CUSTOMER
    @PutMapping("/Update")
    public ResponseEntity UpdateCustomer(@AuthenticationPrincipal Customer customer, @RequestBody @Valid Customer customer1){
        logger.info("Update Customer");
        customerService.updateCustomer(customer.getId(),customer1);
        return ResponseEntity.ok().body(new ApiResponse("Customer Update"));

    }
    //ADMIN
    @DeleteMapping("/delete/{CustomerId}")
    public ResponseEntity deleteCustomer( @PathVariable Integer CustomerId){
        logger.info("delete Customer");
        customerService.deleteCustomer(CustomerId);
        return ResponseEntity.ok().body(new ApiResponse("Customer Deleted"));

    }
}
