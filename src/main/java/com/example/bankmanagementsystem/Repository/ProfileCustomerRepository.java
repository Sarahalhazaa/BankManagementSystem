package com.example.bankmanagementsystem.Repository;

import com.example.bankmanagementsystem.Model.ProfileCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileCustomerRepository extends JpaRepository<ProfileCustomer,Integer> {
    ProfileCustomer findByCustomerId(int customerId);
}
