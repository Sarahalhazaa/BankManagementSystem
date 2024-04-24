package com.example.bankmanagementsystem.Service;

import com.example.bankmanagementsystem.Api.ApiException;
import com.example.bankmanagementsystem.Model.Account;
import com.example.bankmanagementsystem.Model.Customer;
import com.example.bankmanagementsystem.Model.Employee;
import com.example.bankmanagementsystem.Model.User;
import com.example.bankmanagementsystem.Repository.AccountRepository;
import com.example.bankmanagementsystem.Repository.AuthRepository;
import com.example.bankmanagementsystem.Repository.CustomerRepository;
import com.example.bankmanagementsystem.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
private final CustomerRepository customerRepository;
private final AuthRepository authRepository;

    //ADMIN
    public List<Account> ListUsersAccounts() {
        return accountRepository.findAll();
    }

    //CUSTOMER
    public void createNewAccount(Integer customerId, Account account) {
        Customer customer1 = customerRepository.findCustomerById(customerId);
        account.setCustomer(customer1);
         accountRepository.save(account);
    }


    //CUSTOMER
    public void updateAccount( Integer customerId, Integer accountId , Account account) {
        Account account1 = accountRepository.findAccountById(accountId);
        if (account1 == null) {
            throw new ApiException("not found");
        }
        if (account1.getCustomer().getId() != customerId) {
            throw new ApiException("not equal customer id");
        }

       account1.setIsActive(account.getIsActive());
        account1.setBalance(account.getBalance());

       accountRepository.save(account1);


    }

    // CUSTOMER
    public void deleteAccount(Integer customerId ,Integer accountId) {
        Account account1 = accountRepository.findAccountById(accountId);
        if (account1 == null) {
            throw new ApiException("not found");
        }
        if (account1.getCustomer().getId() != customerId) {
            throw new ApiException("not equal customer id");
        }

       accountRepository.delete(account1);
    }

 //   ---------------------  CRUD  -------------------------
//EMPLOYEE
 public void  ActiveBankAccount( Integer accountId ) {

     Account account1 = accountRepository.findAccountById(accountId);
     if (account1 == null) {
         throw new ApiException("not found");
     }
     account1.setIsActive(true);
     accountRepository.save(account1);
 }
//Customer
    public Account ViewAccountDetails( Integer customerId ,Integer accountId) {
        Account account1 = accountRepository.findAccountById(accountId);
        if (account1 == null) {
            throw new ApiException("not found");
        }
        if (account1.getCustomer().getId() != customerId) {
            throw new ApiException("not equal customer id");
        }
        return account1;
    }

    //EMPLOYEE
    public void  BlockBankAccount(  Integer accountId ) {
            Account account1 = accountRepository.findAccountById(accountId);
            if (account1 == null) {
                throw new ApiException("not found");
            }
            account1.setIsActive(false);
            accountRepository.save(account1);
    }
    //Customer
    public void  DepositMoney( Integer customerId, Integer accountId , Integer value) {

        Account account1 = accountRepository.findAccountById(accountId);
        if (account1 == null) {
            throw new ApiException("not found");
        }

        if (account1.getCustomer().getId() != customerId) {
            throw new ApiException("not equal customer id");
        }
        if (account1.getIsActive().equals(false)) {
            throw new ApiException("The account is inactive. the operation could not be completed");
        }
             account1.setBalance(account1.getBalance() + value);
             accountRepository.save(account1);
    }

    //Customer
public void  WithdrawMoney( Integer customerId, Integer accountId , Integer value) {

    Account account1 = accountRepository.findAccountById(accountId);
    if (account1 == null) {
        throw new ApiException("not found");
    }
    if (account1.getCustomer().getId() != customerId) {
        throw new ApiException("not equal customer id");
    }
    if (account1.getIsActive().equals(false)) {
        throw new ApiException("The account is inactive. the operation could not be completed");
    }
    if (account1.getBalance() >= value) {
        account1.setBalance(account1.getBalance() - value);
        accountRepository.save(account1); }
    }
    //Customer
    public void TransferFundsBetweenAccounts( Integer customerId, Integer accountId1 , Integer accountId2 , Integer value) {

        Account account1 = accountRepository.findAccountById(accountId1);
        Account account2 = accountRepository.findAccountById(accountId2);
        if (account1 == null || account2 == null) {
            throw new ApiException("not found");
        }

        if (account1.getCustomer().getId() != customerId || account2.getCustomer().getId() != customerId) {
            throw new ApiException("not equal customer id");
        }

        if (account1.getIsActive().equals(false) || account2.getIsActive().equals(false)) {
            throw new ApiException("The account is inactive. the operation could not be completed");
        }

        if (account1.getBalance() >= value) {
            account1.setBalance(account1.getBalance() - value);
            account2.setBalance(account2.getBalance() + value);
            accountRepository.save(account1);
            accountRepository.save(account2);
        }
    }


}
