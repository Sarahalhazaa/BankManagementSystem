package com.example.bankmanagementsystem.DTO;


import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDTO {

    private Integer employeeId;

    private String position;

    @Positive
    private Integer salary;

}
