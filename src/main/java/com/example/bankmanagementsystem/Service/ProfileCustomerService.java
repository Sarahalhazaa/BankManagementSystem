package com.example.bankmanagementsystem.Service;

import com.example.bankmanagementsystem.Api.ApiException;
import com.example.bankmanagementsystem.DTO.CustomerDTO;
import com.example.bankmanagementsystem.Model.Customer;
import com.example.bankmanagementsystem.Model.ProfileCustomer;
import com.example.bankmanagementsystem.Repository.CustomerRepository;
import com.example.bankmanagementsystem.Repository.ProfileCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileCustomerService {

    private final ProfileCustomerRepository profileCustomerRepository;
    private final CustomerRepository customerRepository;
//ADMIN
    public List<ProfileCustomer> getProfileCustomer() {
        return profileCustomerRepository.findAll();
    }
    //CUSTOMER
    public void addProfile( Integer customerId, CustomerDTO customerDTO) {
        Customer customer1 = customerRepository.findCustomerById(customerId);

        ProfileCustomer profile =new ProfileCustomer(null,customerDTO.getPhoneNumber(),customer1);
        profileCustomerRepository.save(profile);
    }
// CUSTOMER
    public void updateProfile(Integer customerId, CustomerDTO customerDTO) {
        ProfileCustomer profile = profileCustomerRepository.findByCustomerId(customerId);
        if (profile == null) {
            throw new ApiException("profile not found");
        }

        profile.setPhoneNumber(customerDTO.getPhoneNumber());
       profileCustomerRepository.save(profile);

    }
//CUSTOMER
    public void deleteProfile(Integer customerId) {
        ProfileCustomer profile = profileCustomerRepository.findByCustomerId(customerId);
        if (profile == null) {
            throw new ApiException("id not found");
        }
        profileCustomerRepository.delete(profile);
    }


}
