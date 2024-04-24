package com.example.bankmanagementsystem.Repository;

import com.example.bankmanagementsystem.Model.ProfileEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileEmployeeRepository extends JpaRepository<ProfileEmployee,Integer> {

    ProfileEmployee findByEmployeeId(int employeeId);
}
