package com.example.bankmanagementsystem.Controller;

import com.example.bankmanagementsystem.Api.ApiResponse;
import com.example.bankmanagementsystem.DTO.EmployeeDTO;
import com.example.bankmanagementsystem.Model.Employee;
import com.example.bankmanagementsystem.Service.ProfileEmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profileEmployee")
@RequiredArgsConstructor
public class ProfileEmployeeController {
    Logger logger = LoggerFactory.getLogger(ProfileEmployeeService.class);
    private final ProfileEmployeeService profileEmployeeService;

    //ADMIN
    @GetMapping("/get")
    public ResponseEntity getProfileEmployees(){
        logger.info("get Profile Employees");
        return ResponseEntity.status(200).body(profileEmployeeService.getProfileEmployee());
    }

    //EMPLOYEE
    @PostMapping("/register")
    public ResponseEntity addProfileEmployee(@AuthenticationPrincipal Employee employee, @RequestBody @Valid EmployeeDTO employeeDTO){
        logger.info("add Profile Employee");
        profileEmployeeService.addProfile(employee.getId(), employeeDTO);
        return ResponseEntity.ok().body("Profile Employee added successfully");
    }
    //EMPLOYEE
    @PutMapping("/Update")
    public ResponseEntity UpdateProfileEmployee(@AuthenticationPrincipal Employee employee, @RequestBody @Valid EmployeeDTO employeeDTO){
        logger.info("Update Profile Employee");
       profileEmployeeService.updateProfile(employee.getId(), employeeDTO);
        return ResponseEntity.ok().body(new ApiResponse("Profile Employee Update"));

    }
    //EMPLOYEE
    @DeleteMapping("/delete")
    public ResponseEntity deleteProfileEmployee( @AuthenticationPrincipal Employee employee){
        logger.info("delete Profile Employee");
        profileEmployeeService.deleteProfile(employee.getId());
        return ResponseEntity.ok().body(new ApiResponse("Profile Employee Deleted"));

    }
}
