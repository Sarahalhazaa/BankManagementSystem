package com.example.bankmanagementsystem.Controller;

import com.example.bankmanagementsystem.Api.ApiResponse;
import com.example.bankmanagementsystem.Model.Employee;
import com.example.bankmanagementsystem.Model.User;
import com.example.bankmanagementsystem.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeService employeeService;


    //ADMIN
    @GetMapping("/get")
    public ResponseEntity getEmployee(){
        logger.info("get Employee");
        return ResponseEntity.status(200).body(employeeService.getEmployee());
    }

    //EMPLOYEE
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User user){
        logger.info("register Employee");
        employeeService.register(user);
        return ResponseEntity.ok().body("Employee added successfully");
    }
    //EMPLOYEE
    @PutMapping("/Update")
    public ResponseEntity UpdateEmployee(@AuthenticationPrincipal Employee employee, @RequestBody @Valid Employee employee1){
        logger.info("Update Employee");
       employeeService.updateEmployee(employee.getId(), employee1);
        return ResponseEntity.ok().body(new ApiResponse("Employee Update"));

    }
    //ADMIN
    @DeleteMapping("/delete/{EmployeeId}")
    public ResponseEntity deleteEmployee( @PathVariable Integer EmployeeId){
        logger.info("delete Employee");
        employeeService.deleteEmployee(EmployeeId);
        return ResponseEntity.ok().body(new ApiResponse("Employee Deleted"));

    }
}
