package com.example.bankmanagementsystem.Controller;

import com.example.bankmanagementsystem.Api.ApiResponse;
import com.example.bankmanagementsystem.Model.Account;
import com.example.bankmanagementsystem.Model.Customer;
import com.example.bankmanagementsystem.Model.User;
import com.example.bankmanagementsystem.Service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    Logger logger = LoggerFactory.getLogger(AccountController.class);
    private final AccountService accountService;
//ADMIN
    @GetMapping("/get")
    public ResponseEntity ListUsersAccounts(){
        logger.info("get all Accounts");
        return ResponseEntity.status(200).body(accountService.ListUsersAccounts());
    }

//CUSTOMER
    @PostMapping("/add")
    public ResponseEntity  createNewAccount(@AuthenticationPrincipal Customer customer, @RequestBody @Valid Account account){
        logger.info("add account");
        accountService.createNewAccount(customer.getId(),account);
        return ResponseEntity.ok().body("Account added successfully");
    }
    //CUSTOMER
    @PutMapping("/Update/{accountId}")
    public ResponseEntity UpdateAccount( @AuthenticationPrincipal Customer customer , @PathVariable Integer accountId, @RequestBody @Valid Account account){
        logger.info("update account");
        accountService.updateAccount(customer.getId(),accountId,account);
        return ResponseEntity.ok().body(new ApiResponse("Account Updated"));

    }
    //CUSTOMER
    @DeleteMapping("/delete/{accountId}")
    public ResponseEntity deleteUser(@AuthenticationPrincipal Customer customer , @PathVariable Integer accountId){
        logger.info("delete account");
       accountService.deleteAccount(customer.getId(),accountId);
        return ResponseEntity.ok().body(new ApiResponse("Account Deleted"));

    }

   // --------------------------   CRUD     -----------------------------
//EMPLOYEE
    @PutMapping("/ActiveBankAccount/{accountId}")
    public ResponseEntity ActiveBankAccount( @PathVariable Integer accountId){
        logger.info("Active Bank Account");
        accountService.ActiveBankAccount(accountId);
        return ResponseEntity.ok().body(new ApiResponse("Updated"));

    }
//EMPLOYEE
    @PutMapping("/BlockBankAccount/{accountId}")
    public ResponseEntity BlockBankAccount(  @PathVariable Integer accountId){
        logger.info("Block Bank Account");
        accountService.BlockBankAccount(accountId);
        return ResponseEntity.ok().body(new ApiResponse("Updated"));

    }
    //CUSTOMER
    @GetMapping("/ListUsersAccounts/{accountId}")
    public ResponseEntity ListUsersAccounts(@AuthenticationPrincipal Customer customer, @PathVariable Integer accountId){
        logger.info("List Users Accounts");
        return ResponseEntity.status(200).body(accountService.ViewAccountDetails(customer.getId(),accountId));
    }
    //CUSTOMER
    @PutMapping("/DepositMoney/{accountId}/{value}")
    public ResponseEntity DepositMoney( @AuthenticationPrincipal Customer customer, @PathVariable Integer accountId , @PathVariable Integer value){
        logger.info("update account");
        accountService.DepositMoney(customer.getId(),accountId,value);
        return ResponseEntity.ok().body(new ApiResponse("Updated"));

    }
    //CUSTOMER
    @PutMapping("/WithdrawMoney/{accountId}/{value}")
    public ResponseEntity WithdrawMoney( @AuthenticationPrincipal Customer customer, @PathVariable Integer accountId , @PathVariable Integer value){
        logger.info("update account");
        accountService.WithdrawMoney(customer.getId(),accountId,value);
        return ResponseEntity.ok().body(new ApiResponse("Updated"));

    }
    //CUSTOMER
    @PutMapping("/TransferFundsBetweenAccounts/{accountId1}/{accountId2}/{value}")
    public ResponseEntity TransferFundsBetweenAccounts( @AuthenticationPrincipal Customer customer, @PathVariable Integer accountId1 , @PathVariable Integer accountId2 , @PathVariable Integer value){
        logger.info("update account");
        accountService.TransferFundsBetweenAccounts(customer.getId(),accountId1,accountId2,value);
        return ResponseEntity.ok().body(new ApiResponse("Updated"));

    }
}
