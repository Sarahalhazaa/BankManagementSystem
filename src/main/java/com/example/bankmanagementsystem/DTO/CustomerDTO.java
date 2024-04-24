package com.example.bankmanagementsystem.DTO;


import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {

    private Integer CustomerId;

    @Pattern(regexp = "05\\d{8}")
    private String phoneNumber;

}
