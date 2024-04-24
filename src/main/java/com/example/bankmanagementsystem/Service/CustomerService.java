package com.example.bankmanagementsystem.Service;

import com.example.bankmanagementsystem.Api.ApiException;
import com.example.bankmanagementsystem.Model.Customer;
import com.example.bankmanagementsystem.Model.User;
import com.example.bankmanagementsystem.Repository.AuthRepository;
import com.example.bankmanagementsystem.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AuthRepository authRepository;


    //ADMIN
    public List<Customer> getCustomer() {
        return customerRepository.findAll();
    }

    //CUSTOMER
    public void register(User user) {

        user.setRole("CUSTOMER");
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        authRepository.save(user);
        Customer customer = new Customer();
        customer.setUser(user);
        customerRepository.save(customer);

    }
    //CUSTOMER
    public void updateCustomer( Integer customerId, Customer customer) {

        Customer customer1 = customerRepository.findCustomerById(customerId);
        if (customer1 == null) {
            throw new ApiException("not found");
        }
        customer1.setProfileCustomer(customer.getProfileCustomer());
        customer1.setUser(customer.getUser());
        customerRepository.save(customer1);

    }

    //ADMIN
    public void deleteCustomer(Integer customerId) {
        Customer customer1 = customerRepository.findCustomerById(customerId);
        if (customer1 == null) {
            throw new ApiException("not found");
        }
       customerRepository.delete(customer1);
    }

}
